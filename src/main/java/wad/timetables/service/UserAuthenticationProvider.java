package wad.timetables.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import wad.timetables.domain.User;

@Service
public class UserAuthenticationProvider implements AuthenticationProvider{
    
    @Autowired
    private UserService userService;
    
    @Override
    public Authentication authenticate(Authentication a) throws AuthenticationException {
        String username = a.getName();
        String password = a.getCredentials().toString();
        User authUser = userService.getUserByName(username);
        
        if (authUser!=null && authUser.getName().equals(username) && authUser.getPassword().equals(password)) {
            
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("user"));
            
            return new UsernamePasswordAuthenticationToken(username, password,grantedAuths);
        }
        throw new AuthenticationException("Username or password was incorrect.") {};
    }

    @Override
    public boolean supports(Class authType) {
        return authType.equals(UsernamePasswordAuthenticationToken.class);
    }
    
}
