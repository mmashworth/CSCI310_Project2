<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import = "collage.Picture" %>
    <%@ page import = "collage.Collage" %>
    <%@ page import = "java.awt.Graphics2D" %>
	<%@ page import = "java.io.File" %>
	<%@ page import = "java.awt.image.BufferedImage" %>
    <%@ page import = "java.awt.Color" %>
    <%@ page import = "java.io.File" %>
    
	<%@ page import = "java.io.FileOutputStream" %>
	<%@ page import = "java.io.IOException" %>
	
	<%@ page import = "java.io.ByteArrayOutputStream" %>
	<%@ page import = "javax.imageio.ImageIO" %>


<%@ page import = "com.itextpdf.text.Document" %>
<%@ page import = "com.itextpdf.text.DocumentException" %>
<%@ page import = "com.itextpdf.text.pdf.PdfWriter" %>
<%@ page import = "com.itextpdf.text.pdf.PdfContentByte" %>
<%@ page import = "com.itextpdf.text.Image" %>



    
    
    
    <% 
   // String fileName = application.getRealPath("/") + "exportedCollagePDF.pdf";
   // System.out.println("fileName: " + fileName);
   // currentCollage.writeImage(fileName, "pdf");
    %>
    <%
    
    System.out.println("in ExportCollagePDF.jsp");
    Picture currentCollage = (Picture)request.getSession().getAttribute("collageImage");
    currentCollage.makePDF();
 
    
    %>
       
 <!-- 
for (int i = 0; i < slide.length; i++) {
    BufferedImage img = new BufferedImage(pgsize.width, pgsize.height,   BufferedImage.TYPE_INT_RGB);
    Graphics2D graphics = img.createGraphics();
    graphics.setPaint(Color.white);
    graphics.fill(new Rectangle(0, 0, pgsize.width, pgsize.height));
    slide[i].draw(graphics);
    fileName="C:/DATASTORE/slide-"+(i+1)+".png";
    FileOutputStream out = new FileOutputStream(fileName);
    javax.imageio.ImageIO.write(img, "png", out);
out.flush();
out.close();
com.lowagie.text.Image image =com.lowagie.text.Image.getInstance(fileName);
            image.setWidthPercentage(40.0f);
    doc.add((image));
    }

doc.close();
} catch(DocumentException de) {
          System.err.println(de.getMessage());
    }



 -->  
    
    
    
    
    
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Export Collage</title>
</head>
<body>

</body>
</html>

