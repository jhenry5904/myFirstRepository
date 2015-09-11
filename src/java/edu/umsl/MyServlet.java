/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.umsl;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Josh
 */
@WebServlet(name = "MyServlet", urlPatterns = {"/MyServlet"})
public class MyServlet extends HttpServlet 
{
    String teamname;
    String comments;
    int q1;
    int q2;
    int q3;
    int q4;
    double avg;
    private Connection myConnection;
    private Statement myStatement;
    private ResultSet myResultSet;
    
//    public MyServlet(String databaseDriver, String databaseURL)
//    {
//        
//      try
//      {
//         DriverManager.registerDriver(new org.apache.derby.jdbc.ClientDriver());
//         // connect to database
//         myConnection = DriverManager.getConnection( databaseURL );
//
//         // create Statement for executing SQL
//         myStatement = myConnection.createStatement();
//      }
//      catch ( SQLException exception )
//      {
//         exception.printStackTrace();
//      }
//    
//    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        //String databaseDriver = "org.apache.derby.jdbc.ClientDriver";
        //String databaseURL = "jdbc:derby://localhost:1527/Evaluation;create=true;user=me;password=mine";
       
         
        //MyServlet myServlet = new MyServlet( databaseDriver, databaseURL );
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet MyServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MyServlet at " + request.getContextPath() + "</h1>");
            
            //loadTeams();
            teamname = request.getParameter("Teams");
            q1 = Integer.parseInt(request.getParameter("rangeInput"));
            q2 = Integer.parseInt(request.getParameter("rangeInput2"));
            q3 = Integer.parseInt(request.getParameter("rangeInput3"));
            q4 = Integer.parseInt(request.getParameter("rangeInput4"));
            comments = request.getParameter("comments");
            avg = (q1 + q2 + q3 + q4) / 4.0;
            out.println("<br>Teamname = " + teamname);
            out.println("<br>q1 = "+ q1);
            out.println("<br>q2 = "+ q2);
            out.println("<br>q3 = "+ q3);
            out.println("<br>q4 = "+ q4);
            out.println("<br>Comments = "+ comments);
            out.println("<br>Average = "+ avg);
            
//            if (teamname == null)
//            {
//                out.println("<p>Teams: MISSING</p>");
//            }
//            else if (teamname.equals("Team 1"))
//            {
//                out.println("Team 1");
//                q1 = Integer.parseInt(request.getParameter("amount"));
//            }
//            else if (teamname.equals("Team 2"))
//            {
//                out.println("Team 2");
//                 q2 = Integer.parseInt(request.getParameter("amount2"));
//            }
//            else if (teamname.equals("Team 3"))
//            {
//                out.println("Team 3");
//                 q3 = Integer.parseInt(request.getParameter("amount3"));
//            }
//            else if (teamname.equals("Team 4"))
//            {
//                out.println("Team 4");
//                 q4 = Integer.parseInt(request.getParameter("amount4"));
//            }
            
            
//            comments = request.getParameter("comments");
//            if (comments == null || (comments = htmlFilter(comments.trim())).length() == 0)
//            {
//                 out.println("<p>Name: MISSING</p>");
//            } 
//            else 
//            {
//            out.println("<p>Comments: " + comments + "</p>");
//            }
//            
//         if(request.getParameter("calcAvgButton") != null)
//         {
//             avg = (q1 + q2 + q3 + q4) / 4.0;
//             request.setAttribute("calcAvgText", avg);
//             
//         }
//         
//         if (request.getParameter("submitButton") != null)
//         {
//             //updateTeams();
//             
//         }
//         
//         String calcAvgText = request.getParameter("calcAvgText");
//         
//         if (calcAvgText == null || (calcAvgText = htmlFilter(calcAvgText.trim())).length() == 0) 
//         {
//            out.println("<p>Password: MISSING</p>");
//         } 
//         else 
//         {
//            out.println("<p>Password: " + calcAvgText + "</p>");
//         }
// 
         //out.println("<a href='form_input.html'>BACK</a>");
 
         out.println("</body></html>");
      } finally 
        {
         out.close();  // Always close the output writer
        }
   
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
            throws ServletException, IOException 
    {
        
        PrintWriter out = response.getWriter();
        out.println("You are using doGet");
       // loadTeams();
        processRequest(request, response);
        //updateTeams();
      
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
            throws ServletException, IOException 
    {
        PrintWriter out = response.getWriter();
        out.println("You are using doPost");
        processRequest(request, response);
        
    }
    
    
    private void loadTeams()
    {
         // get all account numbers from database
      try
      {
        
          myResultSet = myStatement.executeQuery( "SELECT DISTINCT TEAMNAME FROM APP.TEAMS");
       
         while ( myResultSet.next() )
         {
               //teamComboBox.addItem(myResultSet.getString( "TEAMNAME" ) );
         }

         myResultSet.close(); // close myResultSet

      } // end try

      catch ( SQLException exception )
      {
         exception.printStackTrace();
      }
    }
    
    private void updateTeams()
   {
      // update balance in database
      try
      {
       
          //
          String sql = "UPDATE APP.TEAMS SET Q1TECH = " + q1 +  "," + "Q2CLARITY = " + q2 + ", Q3USEFUL = " + q3 + 
                  ", Q4OVERALL = " + q4 + ", TEAMAVG = " + avg + ", COMMENTS = '" + comments +
                  "' WHERE " + "TEAMNAME = " + "'" + teamname + "'"  ;
          //String sql2 =  "UPDATE APP.TEAMEVAL" + " SET q2 = " + q2 + " WHERE " +
           //            "TEAMNAME = '" + myteamname + "'" + "and course = '" + courseName + "'";
          myStatement.executeUpdate(sql);
          //myStatement.executeUpdate(sql2);
         
      }
      catch ( SQLException exception )
      {
         exception.printStackTrace();
      }
   }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() 
    {
        return "Short description";
    }// </editor-fold>

}
