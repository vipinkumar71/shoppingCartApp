package shoppingCart.App.Security;

import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    Logger logger= LoggerFactory.getLogger(JwtAuthenticationFilter.class);
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        // getToken from Header
        String requestToken = request.getHeader("Authorization");
        logger.info("message {}" ,requestToken);

        String Username=null;
        String jwtToken=null;



        if(requestToken!=null && requestToken.trim().startsWith("Bearer")){
            // get Actual token
            jwtToken=requestToken.substring(7);

            try {

                Username=this.jwtHelper.getUsername(jwtToken);

            }catch(ExpiredJwtException e) {
                logger.info("Invaild token message","Jwt token expire");
            }catch(MalformedJwtException e) {
                logger.info("Invaild token message","Invalid Jwt Token");
            }catch(IllegalArgumentException e) {
                logger.info("Invaild token message","Unable to get token");
            }

            if(Username!=null && SecurityContextHolder.getContext().getAuthentication() == null){

                //Validate
                UserDetails userDetails=this.userDetailsService.loadUserByUsername(Username);

                if(this.jwtHelper.validateToken(jwtToken,userDetails)){

                    UsernamePasswordAuthenticationToken auth=new
                            UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                    SecurityContextHolder.getContext().setAuthentication(auth);

                }else {
                    logger.info("Not validate Message","invalid Jwt Token");

                }
            }else {
                logger.info("User Message","username is null or auth is allready there");
            }


        }else {
            logger.info("Token message {}","token does not start with bearer");
        }
        filterChain.doFilter(request,response);
    }
}