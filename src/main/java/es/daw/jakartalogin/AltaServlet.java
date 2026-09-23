package es.daw.jakartalogin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet("/alta")
public class AltaServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(AltaServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Leer el fichero de texto tegnologías.txt y cargar en un ArrayList
        List<String> tecnologias = new ArrayList<>();
        try {
            tecnologias = leerFichero("/WEB-INF/datos/tecnologias.txt");
        } catch (IOException e) {
            request.setAttribute("mensajeError", e.getMessage());
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
        request.setAttribute("tecnologias", tecnologias);
        request.getRequestDispatcher("/formulario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String email = request.getParameter("email");
        String tecnologia= request.getParameter("tecnologia");
        String nivel = request.getParameter("nivel");

        request.setAttribute("tecnologia", tecnologia);
        request.setAttribute("nombre", nombre);
        request.setAttribute("email", email);
        request.setAttribute("nivel", nivel);

        request.getRequestDispatcher("/confirmacion.jsp").forward(request, response);
    }

    private List<String> leerFichero(String rutaFichero) throws IOException {
        List<String> lista = new ArrayList<>();

        InputStream is = getServletContext().getResourceAsStream(rutaFichero);

        if (is == null) {
            //TODO Trabajar con excepciones propias
            throw new IOException("No se encuentra el fichero "+rutaFichero);
        }

        try(BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    lista.add(linea);
                }
            }
        }

        return lista;
    }
}