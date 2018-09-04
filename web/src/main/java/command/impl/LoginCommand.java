package command.impl;

import com.gmail.sshekh.RoleService;
import com.gmail.sshekh.UserService;
import com.gmail.sshekh.config.ConfigurationManager;
import com.gmail.sshekh.dao.impl.RoleServiceImpl;
import com.gmail.sshekh.dao.impl.UserServiceImpl;
import com.gmail.sshekh.dto.RoleDTO;
import com.gmail.sshekh.dto.UserDTO;
import command.Command;
import util.UserPrincipalConverter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCommand implements Command {

    private UserService userService = new UserServiceImpl();
    private RoleService roleService=new RoleServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && !email.equals("")) {
            UserDTO userByUsername = userService.findUserByEmail(email);
            if (userByUsername != null) {
                if (userByUsername.getPassword().equals(password.trim())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("user", UserPrincipalConverter.toUserPrincipal(userByUsername));
                    Long roleId=userByUsername.getRoleId();
                    RoleDTO roleById=roleService.findRoleById(roleId);
                    String role=roleById.getRoleName();

                    switch (role) {
                        case "Admin":
                            response.sendRedirect("/dispatcher?command=users");
                            break;
                        case "User":
                            response.sendRedirect("/dispatcher?command=items");
                            break;
                        default:
                            response.sendRedirect("/dispatcher?command=login");
                            break;
                    }
                    return null;
                } else {
                    request.setAttribute("error", "Password is not correct!");
                    return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
                }
            } else {
                request.setAttribute("error", "Username is not correct!");
                return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
            }
        } else {
            return ConfigurationManager.getInstance().getProperty(ConfigurationManager.LOGIN_PAGE_PATH);
        }
    }
}
