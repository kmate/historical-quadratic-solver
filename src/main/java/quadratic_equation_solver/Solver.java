/**
 * Reproduction of a quadratic equation solver implemented back in 2005,
 * on a boring afternoon when coming home from 2nd grade of high school...
 * See the README.md for more details!
 */
package quadratic_equation_solver;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Solver extends JFrame {

    public static void main(String[] args) {
        Solver s = new Solver();
        s.szamol();
        s.setLocation(300, 300);
        s.setSize(400, 400);
        s.setVisible(true);
    }

    int _a = 6, _b = 11, _c = -35;

    double[] _ret = new double[4];
    int _lnko;

    String a_, b_, c_;

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        a_ = _a < 0 ? "(" + _a + ")" : String.valueOf(_a);
        b_ = _b < 0 ? "(" + _b + ")" : String.valueOf(_b);
        c_ = _c < 0 ? "(" + _c + ")" : String.valueOf(_c);

        drawString(g, "x=", 2, 20 - 10, SIZE_MEDIUM, Font.ITALIC, LEFT | TOP);
        drawLine(g, 21, 25 - 10, 120, 25 - 10);
        drawString(g, "-" + b_, 28, 22 - 10, SIZE_SMALL, Font.ITALIC, HCENTER | BOTTOM);
        drawString(g, b_ + "^2 - 4*" + a_ + "*" + c_, 88, 22 - 10, SIZE_SMALL, Font.ITALIC, HCENTER | BOTTOM);
        drawString(g, "2 * " + a_, 72, 28 - 10, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);
        drawString(g, "+", 42 + 1, 28 - 15, SIZE_SMALL, Font.PLAIN | UNDERLINED, HCENTER | BOTTOM);

        drawLine(g, 47, 15 - 10, 49, 22 - 10);
        drawLine(g, 49, 22 - 10, 52, 12 - 10);
        drawLine(g, 52, 12 - 10, 124, 12 - 10);
        drawLine(g, 124, 12 - 10, 124, 20 - 10);

        if (_ret[3] > 0) {
            drawString(g, "x=", 2, 70 + 10, SIZE_MEDIUM, Font.ITALIC, LEFT | TOP);
            drawLine(g, 21, 72 + 10, 40, 60 + 10);
            drawLine(g, 21, 75 + 10, 40, 77 + 10);

            if (_ret[1] / _ret[0] == Math.floor(_ret[1] / _ret[0])) {
                drawString(g, String.valueOf((int) (_ret[1] / _ret[0])), 45, 70 - 5, SIZE_SMALL, Font.ITALIC, LEFT | TOP);
            } else {
                _lnko = lnko((int) _ret[1], (int) _ret[0]);
                drawString(g, String.valueOf((int) (_ret[1] / _lnko)), 50, 70 - 15, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);
                drawLine(g, 45, 70 - 6, 55, 70 - 6);
                drawString(g, String.valueOf((int) (_ret[0] / _lnko)), 50, 70 - 4, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);
            }

            if (_ret[2] / _ret[0] == Math.floor(_ret[2] / _ret[0])) {
                drawString(g, String.valueOf((int) (_ret[2] / _ret[0])), 45, 70 + 15, SIZE_SMALL, Font.ITALIC, LEFT | TOP);
            } else {
                _lnko = lnko((int) _ret[2], (int) _ret[0]);
                drawString(g, String.valueOf((int) (_ret[2] / _lnko)), 50, 70 + 6, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);
                drawLine(g, 45, 70 + 16, 55, 70 + 16);
                drawString(g, String.valueOf((int) (_ret[0] / _lnko)), 50, 70 + 18, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);
            }

            drawString(g, "A gyök alatt:", 121, 70, SIZE_SMALL, Font.ITALIC, RIGHT | TOP);
            drawString(g, String.valueOf(_ret[3]), 121, 70 + 10, SIZE_SMALL, Font.ITALIC, RIGHT | TOP);
        } else {
            drawString(g, "Negatív a gyök alatt!", 64, 70, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);
        }

        drawString(g, "x=", 2, 20 + 20, SIZE_MEDIUM, Font.ITALIC, LEFT | TOP);
        drawLine(g, 21, 25 + 20, 120, 25 + 20);

        drawString(g, String.valueOf(-1 * _b), 28, 22 + 20, SIZE_SMALL, Font.ITALIC, HCENTER | BOTTOM);

        drawString(g, String.valueOf(_b * _b - 4 * _a * _c), 88, 22 + 20, SIZE_SMALL, Font.ITALIC, HCENTER | BOTTOM);

        drawString(g, String.valueOf(2 * _a), 72, 28 + 20, SIZE_SMALL, Font.ITALIC, HCENTER | TOP);

        drawString(g, "+", 42 + 1, 28 + 15, SIZE_SMALL, Font.PLAIN | UNDERLINED, HCENTER | BOTTOM);

        drawLine(g, 47, 15 + 20, 49, 22 + 20);
        drawLine(g, 49, 22 + 20, 52, 12 + 20);
        drawLine(g, 52, 12 + 20, 124, 12 + 20);
        drawLine(g, 124, 12 + 20, 124, 20 + 20);
    }

    int LEFT = 1;
    int HCENTER = 2;
    int RIGHT = 4;
    int TOP = 8;
    int BOTTOM = 16;
    int SIZE_MEDIUM = 36;
    int SIZE_SMALL = 24;
    int SCALE = 3;
    int UNDERLINED = 2 ^ 10;

    public void drawString(Graphics g, String s, int x, int y, int size, int style, int anchor) {
        g.setFont(new Font("Arial", style, size));
        Graphics2D g2d = (Graphics2D) g;
        FontMetrics metrics = g2d.getFontMetrics();
        int ascent = metrics.getAscent();
        Rectangle2D r = metrics.getStringBounds(s, g);

        if ((style & UNDERLINED) == UNDERLINED) {
            drawLine(g, x, y, x + (int) r.getWidth() / 3, y);
        }

        x *= SCALE;
        y *= SCALE;

        if ((anchor & RIGHT) == RIGHT) {
            x -= (int) r.getWidth();
        } else if ((anchor & HCENTER) == HCENTER) {
            x -= (int) r.getWidth() / 2;
        }

        if ((anchor & TOP) == TOP) {
            y += ascent;
        } else { // should be BOTTOM
            y += ascent - (int) r.getHeight();
        }

        g.drawString(s, x, y);
    }

    public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        g.drawLine(x1 * SCALE, y1 * SCALE, x2 * SCALE, y2 * SCALE);
    }

    public void szamol() {
        _ret[0] = 2 * _a;
        _ret[1] = (-_b + Math.sqrt(_b * _b - 4 * _a * _c));
        _ret[2] = (-_b - Math.sqrt(_b * _b - 4 * _a * _c));
        _ret[3] = Math.floor(Math.sqrt(_b * _b - 4 * _a * _c));
    }

    public int lnko(int u, int v) {
        if (v == 0) {
            return u;
        } else {
            return lnko(v, Math.abs(u - v));
        }
    }
}
