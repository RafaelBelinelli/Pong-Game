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

        this.positionLeft = 500;
        this.positionTop = (height / 2) - 100;
        this.positionRight = 500;
        this.positionBottom = (height / 2) + 100;

        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.enemy);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        this.height = height;
        this.width = width;
    }

    public void draw(Canvas canvas) {
        canvas.drawRect((float) positionLeft, (float) positionTop, (float) positionRight, (float) positionBottom, paint);
    }

    public void update() {
    }

    public void setPosition(double positionX, double positionY) {
        /*if (this.positionTop + 100 < positionY) {
            if (positionTop + 5 >= height - 20) {
                this.positionTop = height - 220;
                this.positionBottom = height - 20;
            }
            else if (positionTop - 5 <= 20) {
                this.positionTop = 20;
                this.positionBottom = 220;
            } else {
                this.positionTop += 5;
                this.positionBottom += 5;
            }
        }
        else if (this.positionTop + 100 > positionY) {
            if (positionTop + 5 >= height - 20) {
                this.positionTop = height - 220;
                this.positionBottom = height - 20;
            }
            else if (positionTop - 5 <= 20) {
                this.positionTop = 20;
                this.positionBottom = 220;
            } else {
                this.positionTop -= 5;
                this.positionBottom -= 5;
            }
        }*/
        this.positionTop = positionY - 100;
        this.positionBottom = positionY + 100;
    }
}
