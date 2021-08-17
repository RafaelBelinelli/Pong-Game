package br.unicamp.jogo;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

public class Ball {
    private final Paint paint;

    private double positionX;
    private double positionY;

    private double positionLeft;
    private double positionTop;
    private double positionRight;
    private double positionBottom;

    private double screenWidth;
    private double screenHeight;

    private double angle;

    private Enemy enemy;

    public Ball (Context context, double height, double width, Enemy enemy) {
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.ball);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        positionLeft = ((width / 2.0) - 25);
        positionTop = ((height / 2.0) - 25);
        positionRight = ((width / 2.0) + 25);
        positionBottom = ((height / 2.0) + 25);

        positionX = width / 2.0;
        positionY = height / 2.0;

        this.screenHeight = height;
        this.screenWidth = width;

        angle = -90;

        this.enemy = enemy;
    }

    boolean inverte = false;
    boolean comecou = false;
    public void draw(Canvas canvas, double positionR, double positionT) {

        if (Math.ceil(positionRight) >= this.screenWidth) {
            angle = -90;
        }

        // Alguem perdeu
        if (Math.ceil(positionLeft) <= 0) {
            comecou = false;
            inverte = false;

            if (Math.ceil(positionLeft) <= 0) {
                angle = 90;
            }
            if (Math.ceil(positionRight) >= this.screenWidth) {
                angle = -90;
            }

            positionLeft = ((this.screenWidth / 2.0) - 25);
            positionTop = ((this.screenHeight / 2.0) - 25);
            positionRight = ((this.screenWidth / 2.0) + 25);
            positionBottom = ((this.screenHeight / 2.0) + 25);

            positionX = screenHeight / 2.0;
            positionY = screenHeight / 2.0;
        }

        // Bateu no chão ou no teto
        if (Math.ceil(positionBottom) >= Math.ceil(screenHeight) || Math.ceil(positionTop) <= 0) {
            inverte = !inverte;
        }

        // Bola rebateu
        if (Math.ceil(positionLeft) <= 100 && ((positionTop + 50 >= positionT && positionTop <= positionT + 200) || (positionT + 50 <= positionBottom && positionT >= positionTop))) {
            comecou = true;
            double middlePlayer = positionT + 100;
            double distanceFromCenter = Math.abs(middlePlayer - positionY);

            angle = 0.35 * distanceFromCenter;

            double aleatory = Math.random();
            inverte = aleatory <= 0.5;
        }

        if (angle > 0) {
            positionX += 10;
            if (comecou) {
                if (inverte) {
                    positionY -= (30.0/Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                } else {
                    positionY += (30.0/Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                }
            }
        } else {
            positionX -= 10;
            if (comecou) {
                if (inverte) {
                    positionY -= (30.0/Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                } else {
                    positionY += (30.0/Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                }
            }
        }

        enemy.setPosition(positionX,positionY);
        enemy.draw(canvas);

        // Bola anda
        positionLeft = positionX - 25;
        positionRight = positionX + 25;
        positionTop = positionY - 25;
        positionBottom = positionY + 25;

        canvas.drawRect((float) positionLeft, (float) positionTop, (float) positionRight, (float) positionBottom, paint);
    }

    public void update() {
    }
}
