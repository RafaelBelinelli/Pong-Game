package br.unicamp.jogo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.ContextCompat;

public class Player {
    private double positionLeft;
    private double positionTop;
    private double positionRight;
    private double positionBottom;
    private Paint paint;
    private Context context;
    private double height;
    private double width;

    public Player(Context context, double positionLeft, double positionTop, double positionRight, double positionBottom, double height, double width) {
        this.context = context;

        this.positionLeft = positionLeft;
        this.positionTop = positionTop;
        this.positionRight = positionRight;
        this.positionBottom = positionBottom;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        this.height = height;
        this.width = width;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect((float) positionLeft, (float) positionTop, (float) positionRight, (float) positionBottom, paint);
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

    public void update() {
    }

    public void setPosition(double positionX, double positionY) {
        if (positionY - 100 <= 20) {
            this.positionTop = 20;
            this.positionBottom = 220;
        } else if (positionY + 100 >= height - 20) {
            this.positionTop = height - 220;
            this.positionBottom = height - 20;
        } else {
            this.positionTop = positionY - 100;
            this.positionBottom = positionY + 100;
        }
    }
}
