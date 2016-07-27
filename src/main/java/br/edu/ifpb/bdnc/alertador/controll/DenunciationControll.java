/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.controll;

import br.edu.ifpb.bdnc.alertador.dao.bddao.DenunciationBdDao;
import br.edu.ifpb.bdnc.alertador.entity.Denunciation;
import br.edu.ifpb.bdnc.alertador.entity.User;
import br.edu.ifpb.bdnc.alertador.enums.DenunciationType;
import br.edu.ifpb.bdnc.alertador.enums.SquealerType;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wensttay
 */
@WebServlet( name = "DenunciationControll", urlPatterns = { "/DenunciationControll" } )
public class DenunciationControll extends HttpServlet{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException{
        try{
            Denunciation d = new Denunciation();
            d.setDescription(request.getParameter("description"));
            d.setDenunciationType(DenunciationType.get(
                    Integer.valueOf(request.getParameter("denunciationType"))));
            d.setSquealerType(SquealerType.get(
                    Integer.valueOf(request.getParameter("squealerType"))));
            d.setSquealer(( User ) request.getSession().getAttribute("user"));

            String lat = request.getParameter("lat");
            String lng = request.getParameter("lng");
            System.out.println(lat + " - " + lng);
            String wktPoint = "POINT(" + lat + " " + lng + ")";
            Point p = ( Point ) new WKTReader().read(wktPoint);
            d.setLocation(p);
            
            String[] check = request.getParameterValues("anonymous");
            
            if ( check != null){
                d.setAnonymous(true);
            } else{
                d.setAnonymous(false);
            }

            DenunciationBdDao bdDao = new DenunciationBdDao();
            String url = "";

            if ( bdDao.save(d) ){
                url = "index";
            } else{
                url = "index";
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

        } catch ( ParseException ex ){
            ex.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException{
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     *
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException{
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo(){
        return "Short description";
    }// </editor-fold>

}
