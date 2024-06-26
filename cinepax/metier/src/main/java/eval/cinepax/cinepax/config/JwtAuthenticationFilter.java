package eval.cinepax.cinepax.config;
// package eval.cinepax.cinepax.config;

// import java.io.IOException;

// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
// import org.springframework.stereotype.Component;
// import org.springframework.web.filter.OncePerRequestFilter;

// import io.micrometer.common.lang.NonNull;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;

// // creation construction pour tout les private 
// // exttends un filtre = une requete
// @Component
// @RequiredArgsConstructor
// public class JwtAuthenticationFilter extends OncePerRequestFilter{

//     private final JwtService jwtService;
//     private final UserDetailsService userDetailsService;

//     @Override
//     protected void doFilterInternal(
//             @NonNull HttpServletRequest request, 
//             @NonNull HttpServletResponse response, 
//             @NonNull FilterChain filterChain)throws ServletException, IOException {

//         // Contient les bearer tokens
//         final String authHeader = request.getHeader("Authorization");
//         final String jwtToken;
//         final String userMail;

//         if( authHeader == null ||! authHeader.startsWith("Bearer")){
//             filterChain.doFilter(request, response);
//             return;
//         }
//         jwtToken = authHeader.substring(7);
//         userMail = jwtService.extractUserMail(jwtToken);
//         if(userMail != null && SecurityContextHolder.getContext().getAuthentication() == null){
//             UserDetails userDetails = this.userDetailsService.loadUserByUsername(userMail);
//             // System.out.println(userDetails);
//             if( jwtService.isTokenValid(jwtToken, userDetails) ){
//                 UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
//                     userDetails,
//                     null,
//                     userDetails.getAuthorities()
//                 );
//                 authToken.setDetails(
//                     new WebAuthenticationDetailsSource().buildDetails(request)
//                 );
//                 SecurityContextHolder.getContext().setAuthentication(authToken);
//             }
//         }
//         filterChain.doFilter(request, response);
//     }
    
// }
