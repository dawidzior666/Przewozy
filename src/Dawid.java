/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author DonDawido
 */
public class Dawid extends HttpServlet {

    double calkprzych = 0;
    int calklos, calktaksi, calkmini, calkauto, liczbapasazerow = 0;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    void walidacja() {
        out.println("");
    } //nieużywana

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        String taryfa = request.getParameter("taryfa");
        int odleglosc = Integer.parseInt(request.getParameter("odleglosc"));
        double srodek = Double.parseDouble(request.getParameter("srodek"));
        double wspolczynnik = 1.0;
        double koszt = 0;
        String nocna = "nie";
        String srodektr = "taksówka";
        try {
            liczbapasazerow = Integer.parseInt(request.getParameter("liczbapasazerow"));
            if (taryfa != null) {
                nocna = "tak";
                wspolczynnik = 1.3;
            }
            koszt = wspolczynnik * liczbapasazerow * srodek * odleglosc;
            calklos = calklos + liczbapasazerow;
            calkprzych = calkprzych + koszt;
            if (srodek == 1) {
                calktaksi = calktaksi + odleglosc;
                srodektr = "taksówka";
            }
            if (srodek == 0.5) {
                calkmini = calkmini + odleglosc;
                srodektr = "minibus";
            }
            if (srodek == 0.1) {
                calkauto = calkauto + odleglosc;
                srodektr = "autokar";
            }
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Dawid</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<h1>Zlecenie na przewóz osób</h1>");

            out.println("<TABLE BORDER CELLSPACING=1 cellpadding=1>"
                    + "<tr><td><b>Środek transportu</td><td>" + srodektr + "</td></b></tr>"
                    + "<tr><td>Liczba pasażerów</td><td>" + liczbapasazerow + " osób</td></tr>"
                    + "<tr><td>Odległość</td><td>" + odleglosc + " km</td></tr> "
                    + "<tr><td>Taryfa Nocna</td><td>" + nocna + "</td></tr>"
                    + "<tr><td>Całkowity koszt przejazdu</td><td>" + koszt + " zł</td></tr></TABLE>");

            out.println("<br/>");
            out.println("<b><font size='4'>Przebiegi sumaryczne </font></b><br>"
                    + "Taksówka: " + calktaksi + " km<br>"
                    + "Minibus: " + calkmini + " km<br>"
                    + "Autokar: " + calkauto + " km<br/><br/>");
            out.println("<b><font size='4'>Przewieziono osób: </b>" + calklos + "</font><br>");
            out.println("<b><font size='4'>Przychód całkowity: </b>" + calkprzych + "</font><br/><br/>");
        } catch (NumberFormatException e) {

            out.println("<h3><font color='red'>Błędnie wprowadzona liczba pasażerów !</font></h3>");

        }

        out.println("<a href='PrzejazdN.html'>Następny przejazd</a>");

        out.println("</body>");
        out.println("</html>");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
