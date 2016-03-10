package com.servlets.test;


import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import javax.servlet.*;
import javax.servlet.http.*;


public class SignInServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String user = request.getParameter("name");
        String pass = request.getParameter("pass");
        try {

            Socket serverSocket = new Socket("localhost", 15151);
            BufferedReader fromSocketServer = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            PrintWriter toSocketServer = new PrintWriter(serverSocket.getOutputStream(), true);


            toSocketServer.println("sign_in " + user + " " + pass);
            String socketServerResponse = fromSocketServer.readLine();

            System.out.println(socketServerResponse);

            out.println("<html><body>");
            out.println(socketServerResponse);
            out.println("</body></html>");


        } catch (SocketException socketException) {
            socketException.printStackTrace();
        } catch (Exception se) {
            se.printStackTrace();

        }
    }
}


