package org.yourorghere;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.texture.Texture;
import com.sun.opengl.util.texture.TextureIO;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;

/**
 * Rick2D.java <BR>
 * author: Najwa Wali
 */
public class Rick2D implements GLEventListener {

    Texture tex; // create object 

    public static void main(String[] args) {
        Frame frame = new Frame("Rick");
        GLCanvas canvas = new GLCanvas();

        canvas.addGLEventListener(new Rick2D());
        frame.add(canvas);
        frame.setSize(500, 500);
        final Animator animator = new Animator(canvas);
        frame.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                // Run this on another thread than the AWT event queue to
                // make sure the call to Animator.stop() completes before
                // exiting
                new Thread(new Runnable() {

                    public void run() {
                        animator.stop();
                        System.exit(0);
                    }
                }).start();
            }
        });
        // Center frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        animator.start();
    }

    public void init(GLAutoDrawable drawable) {
        // Use debug pipeline
        // drawable.setGL(new DebugGL(drawable.getGL()));

        GL gl = drawable.getGL();
        System.err.println("INIT GL IS: " + gl.getClass().getName());

        // Enable VSync
        gl.setSwapInterval(1);

        // Setup the drawing area and shading mode
        gl.glClearColor(0f, 0f, 0f, 0f);
        gl.glShadeModel(GL.GL_SMOOTH); // try setting this to GL_FLAT and see what happens.

        gl.glEnable(GL.GL_TEXTURE_2D); //activate texture mapping for 2D
        try {
            //load textures here
            tex = TextureIO.newTexture(new File("P.png"), true);
        } catch (IOException ex) {
            System.err.println(ex);
        }

    }

    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();

        if (height <= 0) { // avoid a divide by zero error!

            height = 1;
        }
        final float h = (float) width / (float) height;
        gl.glViewport(0, 0, width, height);
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(45.0f, h, 1.0, 20.0);
        gl.glMatrixMode(GL.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();

        // Clear the drawing area
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);
        // Reset the current matrix to the "identity"
        gl.glLoadIdentity();

        gl.glTranslatef(0.0f, 0.0f, -20.0f);

        // BACKGROUND 
        background(gl);

        // DRAWING THE HAIR 
        hair(gl);

        // DRAWING THE FACE 
        face(gl, 500);

        // DRAWING THE EYES 
        eyes(gl, 500);

        // DRAWING A NOSE 
        nose(gl, 500);

        // DRAWING THE MOUTH 
        mouth(gl, 500);

        // Flush all drawing operations to the graphics card
        gl.glFlush();
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
    }

    // =========================================================================
    public void background(GL gl) {

        gl.glEnable(GL.GL_TEXTURE_2D);
        gl.glColor3f(1f, 1f, 1f);
        gl.glBegin(GL.GL_QUADS);
        tex.bind();
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(-9, -9);
        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(9, -9);
        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(9, 9);
        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(-9, 9);
        gl.glEnd();
        gl.glDisable(GL.GL_TEXTURE_2D);

    }

    // =========================================================================
    public void hair(GL gl) {

        // Right Side
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(180 / 255f, 220 / 255f, 245 / 255f);
        gl.glVertex2f(1.5f, -2.5f);
        gl.glVertex2f(2.5f, -3f);
        gl.glVertex2f(2.5f, -2f);
        gl.glVertex2f(4f, -1.5f);
        gl.glVertex2f(3f, -0.5f);
        gl.glVertex2f(5f, 1f);
        gl.glVertex2f(3f, 1.5f);
        gl.glVertex2f(4f, 4f);
        gl.glVertex2f(1.5f, 3.5f);
        gl.glVertex2f(1.5f, 5.5f);
        gl.glVertex2f(0f, 4f);
        gl.glVertex2f(0f, 3f);
        gl.glEnd();

        // Effect
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(119 / 255f, 178 / 255f, 203 / 255f);
        gl.glVertex2f(1.5f, -2.5f);
        gl.glVertex2f(2.5f, -3f);
        gl.glVertex2f(2.5f, -2f);
        gl.glVertex2f(4f, -1.5f);
        gl.glVertex2f(2f, -1f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(108 / 255f, 166 / 255f, 192 / 255f);
        gl.glVertex2f(1.5f, -1f);
        gl.glVertex2f(2.5f, -3f);
        gl.glVertex2f(1.5f, -2.5f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(119 / 255f, 178 / 255f, 203 / 255f);
        gl.glVertex2f(0f, -0.5f);
        gl.glVertex2f(3.3f, -0.8f);
        gl.glVertex2f(5f, 1f);
        gl.glVertex2f(2f, 0.5f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(146 / 255f, 203 / 255f, 239 / 255f);
        gl.glVertex2f(1f, 1f);
        gl.glVertex2f(4f, 4f);
        gl.glVertex2f(3.1f, 1.5f);
        gl.glVertex2f(2f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(146 / 255f, 203 / 255f, 239 / 255f);
        gl.glVertex2f(1f, 3f);
        gl.glVertex2f(1.5f, 3.5f);
        gl.glVertex2f(1.5f, 5.5f);
        gl.glVertex2f(0.5f, 3f);
        gl.glEnd();

        // Left side
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(180 / 255f, 220 / 255f, 245 / 255f);
        gl.glVertex2f(-1.5f, -2.5f);
        gl.glVertex2f(-2.5f, -3f);
        gl.glVertex2f(-2.5f, -2f);
        gl.glVertex2f(-4f, -1.5f);
        gl.glVertex2f(-3f, -0.5f);
        gl.glVertex2f(-5f, 1f);
        gl.glVertex2f(-3f, 1.5f);
        gl.glVertex2f(-4f, 4f);
        gl.glVertex2f(-1.5f, 3.5f);
        gl.glVertex2f(-1.5f, 5.5f);
        gl.glVertex2f(0f, 4f);
        gl.glVertex2f(0f, 3f);
        gl.glEnd();

        // Effect
        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(119 / 255f, 178 / 255f, 203 / 255f);
        gl.glVertex2f(-1.5f, -2.5f);
        gl.glVertex2f(-2.5f, -3f);
        gl.glVertex2f(-2.5f, -2f);
        gl.glVertex2f(-4f, -1.5f);
        gl.glVertex2f(-2f, -1f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(108 / 255f, 166 / 255f, 192 / 255f);
        gl.glVertex2f(-1.5f, -1f);
        gl.glVertex2f(-2.5f, -3f);
        gl.glVertex2f(-1.5f, -2.5f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(119 / 255f, 178 / 255f, 203 / 255f);
        gl.glVertex2f(0f, -0.5f);
        gl.glVertex2f(-3.3f, -0.8f);
        gl.glVertex2f(-5f, 1f);
        gl.glVertex2f(-2f, 0.5f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(146 / 255f, 203 / 255f, 239 / 255f);
        gl.glVertex2f(-1f, 1f);
        gl.glVertex2f(-4f, 4f);
        gl.glVertex2f(-3.1f, 1.5f);
        gl.glVertex2f(-2f, 1f);
        gl.glEnd();

        gl.glBegin(GL.GL_TRIANGLE_FAN);
        gl.glColor3f(146 / 255f, 203 / 255f, 239 / 255f);
        gl.glVertex2f(-1f, 3f);
        gl.glVertex2f(-1.5f, 3.5f);
        gl.glVertex2f(-1.5f, 5.5f);
        gl.glVertex2f(-0.5f, 3f);
        gl.glEnd();

    }
    // =========================================================================

    public void face(GL gl, float numPoints) {

        float Radius = 2f;

        // Draw a Circle (Right Ear)
        float Radius2 = 0.6f;
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(215 / 255f, 189 / 255f, 165 / 255.0f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius2;
            double Y = Math.sin(Angle) * Radius2;
            gl.glVertex2d(X + 1.9, Y);
        }
        // Done Drawing a Circle
        gl.glEnd();

        // Draw The Fourth Circle (Left Ear)
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(215 / 255f, 189 / 255f, 165 / 255.0f);  
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius2;
            double Y = Math.sin(Angle) * Radius2;
            gl.glVertex2d(X - 1.9, Y);
        }
        // Done Drawing The Fourth Circle
        gl.glEnd();

        // Draw A Quad
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        gl.glVertex3f(-2.0f, 1.0f, 0.0f);                     // Top Left
        gl.glVertex3f(2.0f, 1.0f, 0.0f);                      // Top Right
        gl.glVertex3f(2.0f, -1.0f, 0.0f);                     // Bottom Right
        gl.glVertex3f(-2.0f, -1.0f, 0.0f);                    // Bottom Left
        // Done Drawing The Quad
        gl.glEnd();

        // Draw The First Circle
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y + 1.2);
        }
        // Done Drawing The First Circle
        gl.glEnd();

        // Draw The Second Circle
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius;
            double Y = Math.sin(Angle) * Radius;
            gl.glVertex2d(X, Y - 1.2);
        }
        // Done Drawing The Second Circle
        gl.glEnd();

    }
    // =========================================================================

    public void eyes(GL gl, float numPoints) {

        float Radius3 = 0.7f;
        float Radius4 = 0.2f;
        float Radius5 = 0.09f;

        // Right Black bag
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(216 / 255f, 183 / 255f, 147 / 255f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius3 / 1.1;
            double Y = Math.sin(Angle) * Radius3;
            gl.glVertex2d(X + 0.9, Y + 0.2);
        }
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius3 - 0.1 / 1.1;
            double Y = Math.sin(Angle) * Radius3 - 0.1;
            gl.glVertex2d(X + 1.1, Y + 0.4);
        }
        gl.glEnd();

        // Right Black bag
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(216 / 255f, 183 / 255f, 147 / 255f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius3 / 1.1;
            double Y = Math.sin(Angle) * Radius3;
            gl.glVertex2d(X - 0.9, Y + 0.2);
        }
        gl.glEnd();

        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius3 - 0.1 / 1.1;
            double Y = Math.sin(Angle) * Radius3 - 0.1;
            gl.glVertex2d(X - 0.9, Y + 0.4);
        }
        gl.glEnd();

        // Right Eye
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(1f, 1f, 1f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius3;
            double Y = Math.sin(Angle) * Radius3;
            gl.glVertex2d(X + 0.9, Y + 0.5);
        }
        gl.glEnd();

        // Right Lense
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 0f, 0f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius4;
            double Y = Math.sin(Angle) * Radius4;
            gl.glVertex2d(X + 0.9, Y + 0.5);
        }
        gl.glEnd();

        // Right Eye
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(1f, 1f, 1f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius3;
            double Y = Math.sin(Angle) * Radius3;
            gl.glVertex2d(X - 0.9, Y + 0.5);
        }
        gl.glEnd();

        // Left Lense
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(0f, 0f, 0f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius4;
            double Y = Math.sin(Angle) * Radius4;
            gl.glVertex2d(X - 0.9, Y + 0.5);
        }
        gl.glEnd();

        // Draw A Quad (Eyeleds)
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);
        gl.glVertex3f(-1.7f, 0.6f, 0.0f);                     // Top Left
        gl.glVertex3f(1.7f, 0.6f, 0.0f);                      // Top Right
        gl.glVertex3f(1.7f, 1.5f, 0.0f);                     // Bottom Right
        gl.glVertex3f(-1.7f, 1.5f, 0.0f);                    // Bottom Left
        gl.glEnd();

        // Draw A Line (Eyebrows)
        gl.glLineWidth(10);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(180 / 255f, 220 / 255f, 245 / 255f);
        gl.glVertex2f(-1.5f, 1.5f);
        gl.glVertex2f(1.5f, 1.5f);
        gl.glEnd();

        // Left Brow
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(180 / 255f, 220 / 255f, 245 / 255f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius5;
            double Y = Math.sin(Angle) * Radius5;
            gl.glVertex2d(X - 1.5, Y + 1.5);
        }
        gl.glEnd();

        // Right Brow
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(180 / 255f, 220 / 255f, 245 / 255f);
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius5;
            double Y = Math.sin(Angle) * Radius5;
            gl.glVertex2d(X + 1.5, Y + 1.5);
        }

        gl.glEnd();

        // Draw A Line (Forehead)
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(216 / 255f, 183 / 255f, 147 / 255f);
        gl.glVertex2f(-1.2f, 1.9f);
        gl.glVertex2f(1.2f, 1.9f);
        gl.glEnd();

        // Draw A Line (Forehead)
        gl.glLineWidth(3);
        gl.glBegin(GL.GL_LINES);
        gl.glColor3f(216 / 255f, 183 / 255f, 147 / 255f);
        gl.glVertex2f(-0.9f, 2.2f);
        gl.glVertex2f(0.9f, 2.2f);
        gl.glEnd();

    }

    // =========================================================================
    public void nose(GL gl, float numPoints) {

        float Radius6 = 0.6f;
        float Radius7 = 0.5f;

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(186 / 255f, 150 / 255f, 114 / 255f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius6 / 2.5;
            double Y = Math.sin(Angle) * Radius6;
            gl.glVertex2d(X, Y - 0.8);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius7 / 2.5;
            double Y = Math.sin(Angle) * Radius7;
            gl.glVertex2d(X, Y - 0.8);
        }
        gl.glEnd();

        // Draw A Quad 
        gl.glBegin(GL.GL_QUADS);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        gl.glVertex3f(-0.4f, -0.7f, 0.0f);                     // Top Left
        gl.glVertex3f(0.4f, -0.7f, 0.0f);                      // Top Right
        gl.glVertex3f(0.4f, -0.2f, 0.0f);                     // Bottom Right
        gl.glVertex3f(-0.4f, -0.2f, 0.0f);                    // Bottom Left
        gl.glEnd();

    }

    // =========================================================================
    
    public void mouth(GL gl, float numPoints) {

        float Radius8 = 0.9f;
        float Radius9 = 0.8f;
        float Radius10 = 0.2f;
        float Radius11 = 0.3f;

        float Radius12 = 0.1f;
        float Radius13 = 0.3f;

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(186 / 255f, 150 / 255f, 114 / 255f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius10;
            double Y = Math.sin(Angle) * Radius10;
            gl.glVertex2d(X + 1, Y - 2.2);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);  
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius11;
            double Y = Math.sin(Angle) * Radius11;
            gl.glVertex2d(X + 0.8, Y - 2.2);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(186 / 255f, 150 / 255f, 114 / 255f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius8;
            double Y = Math.sin(Angle) * Radius8 / 3.5;
            gl.glVertex2d(X, Y - 2.2);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius9;
            double Y = Math.sin(Angle) * Radius9 / 3.5;
            gl.glVertex2d(X, Y - 2.2);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius9;
            double Y = Math.sin(Angle) * Radius9 / 3.5;
            gl.glVertex2d(X - 0.3, Y - 2.3);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(223 / 255f, 190 / 255f, 161 / 255.0f);  
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius9;
            double Y = Math.sin(Angle) * Radius9 / 3.5;
            gl.glVertex2d(X + 0.1, Y - 2.3);
        }
        gl.glEnd();

        // AlCOHOL
        
        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 253 / 255f, 123 / 255f);   
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius12;
            double Y = Math.sin(Angle) * Radius12;
            gl.glVertex2d(X - 0.4, Y - 2.1);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 253 / 255f, 123 / 255f);  
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius12;
            double Y = Math.sin(Angle) * Radius12;
            gl.glVertex2d(X - 0.3, Y - 2.1);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 253 / 255f, 123 / 255f); 
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius13 / 2;
            double Y = Math.sin(Angle) * Radius13 / 1;
            gl.glVertex2d(X - 0.2, Y - 2.3);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 253 / 255f, 123 / 255f); 
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius12;
            double Y = Math.sin(Angle) * Radius12;
            gl.glVertex2d(X, Y - 2.1);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 253 / 255f, 123 / 255f); 
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius12;
            double Y = Math.sin(Angle) * Radius12;
            gl.glVertex2d(X + 0.1, Y - 2.6);
        }
        gl.glEnd();

        // Draw a Circle 
        gl.glBegin(GL.GL_POLYGON);
        gl.glColor3f(32 / 255f, 253 / 255f, 123 / 255f); 
        for (int i = 0; i < numPoints; i++) {
            double Angle = i * (2.0 * Math.PI / numPoints);
            double X = Math.cos(Angle) * Radius12;
            double Y = Math.sin(Angle) * Radius12;
            gl.glVertex2d(X - 0.6, Y - 2.3);
        }
        gl.glEnd();

    }
}
