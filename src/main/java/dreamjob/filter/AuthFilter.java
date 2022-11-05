package dreamjob.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Интерфейс Filter содержит метод doFilter. Через этот метод будут проходить запросы к сервлетам.
 * Если запрос идет к адресам loginPage или login, то мы их пропускаем сразу.
 * Если запросы идут к другим адресам, то проверяем наличие пользователя в HttpSession.
 * Если его нет, то мы переходим на страницу авторизации.
 *
 * @author Svistunov Mikhail
 * @version 1.1
 * В фильтр добавлен доступ к представлениям для регистрации пользователей.
 */
@Component
public class AuthFilter implements Filter {
    @Override
    public void doFilter(
            ServletRequest request,
            ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("loginPage") || uri.endsWith("login")
            || uri.endsWith("formAddUser") || uri.endsWith("createUser")
            || uri.endsWith("failRegistration") || uri.endsWith("successRegistration")
            || uri.endsWith("index")
        ) {
            chain.doFilter(req, res);
            return;
        }
        if (req.getSession().getAttribute("user") == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }
}

