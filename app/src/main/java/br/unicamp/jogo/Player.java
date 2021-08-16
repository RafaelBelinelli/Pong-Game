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

    public Player(Context context, double positionLeft, double positionTop, double positionRight, double positionBottom) {
        this.positionLeft = positionLeft;
        this.positionTop = positionTop;
        this.positionRight = positionRight;
        this.positionBottom = positionBottom;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.player);
    }

    public void draw(Canvas canvas) {
        paint.setColor(Color.WHITE);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect((float) positionLeft, (float) positionTop, (float) positionRight, (float) positionBottom, paint);
    }

    public void update() {
    }

    public void setPosition(double positionX, double positionY) {
        this.positionTop = positionY - 100;
        this.positionBottom = positionY + 100;
    }
}
