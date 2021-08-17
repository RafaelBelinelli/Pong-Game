package br.unicamp.jogo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Enemy {
    private double positionLeft;
    private double positionTop;
    private double positionRight;
    private double positionBottom;
    private Paint paint;
    private Context context;
    private double height;
    private double width;

    public Enemy(Context context, double height, double width) {
        this.context = context;

        this.positionLeft = width - 100;
        this.positionTop = (height / 2) - 100;
        this.positionRight = width - 50;
        this.positionBottom = (height / 2) + 100;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.enemy);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        this.height = height;
        this.width = width;
    }

    public double getPositionLeft() {
        return positionLeft;
    }

    public double getPositionTop() {
        return positionTop;
    }

    public double getPositionRight() {
        return positionRight;
    }

    public double getPositionBottom() {
        return positionBottom;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect((float) positionLeft, (float) positionTop, (float) positionRight, (float) positionBottom, paint);
    }

    public void update() {
    }

    public void setPosition(double positionX, double positionY) {
        // Pra baixo
        if (this.positionTop + 100 < positionY) {
            this.positionTop += 8;
            this.positionBottom += 8;
        } // Pra cima
        else if (this.positionTop + 100 > positionY) {
            this.positionTop -= 8;
            this.positionBottom -= 8;
        }
    }
}
