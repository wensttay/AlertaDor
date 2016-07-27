/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.edu.ifpb.bdnc.alertador.controll;

import br.edu.ifpb.bdnc.alertador.dao.bddao.DenunciationBdDao;
import br.edu.ifpb.bdnc.alertador.entity.Denunciation;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wensttay
 */
@WebServlet( name = "GetAllMarksControll", urlPatterns = { "/GetAllMarksControll" } )
public class GetAllMarksControll extends HttpServlet{

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
        DenunciationBdDao denunciationBdDao = new DenunciationBdDao();
        String json = toGson(denunciationBdDao.getAll());

        for ( Denunciation denunciation : denunciationBdDao.getAll() ){
            System.out.println(denunciation.toString());
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }

    public static String toGson( List<Denunciation> denuncias ){
        GsonBuilder gb = new GsonBuilder();
        gb.serializeSpecialFloatingPointValues().setExclusionStrategies(new ZPointExclusionStrategy());
        Gson gson = gb.create();

        return gson.toJson(denuncias);
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
