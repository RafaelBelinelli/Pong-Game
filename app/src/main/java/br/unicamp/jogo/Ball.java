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

    private int pontPlayer = 0;
    private int pontCPU = 0;

    public Ball (Context context, double height, double width, Enemy enemy) {
        paint = new Paint();
        int color = ContextCompat.getColor(context, R.color.ball);
        paint.setColor(color);
        paint.setTextSize(80);
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
    int j = 25;
    public void draw(Canvas canvas, double positionR, double positionT) {
        /*for (int i = 0; i <= this.screenHeight; i+=120) {
            j += 20;
            canvas.drawRect((float) 110, (float) j, (float) 130, (float) j + 100, paint);
            j += 100;
        }*/

        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j, (float) (this.screenWidth / 2) + 32, (float) j + 100, paint);
        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j + 150, (float) (this.screenWidth / 2) + 32, (float) j + 250, paint);
        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j + 300, (float) (this.screenWidth / 2) + 32, (float) j + 400, paint);
        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j + 450, (float) (this.screenWidth / 2) + 32, (float) j + 550, paint);
        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j + 600, (float) (this.screenWidth / 2) + 32, (float) j + 700, paint);
        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j + 750, (float) (this.screenWidth / 2) + 32, (float) j + 850, paint);
        canvas.drawRect((float) (this.screenWidth / 2) + 12, (float) j + 900, (float) (this.screenWidth / 2) + 32, (float) j + 1000, paint);

        // Alguem perdeu
        if (Math.ceil(positionRight) <= 0 || Math.ceil(positionLeft) >= this.screenWidth) {
            comecou = false;
            inverte = false;

            if (Math.ceil(positionLeft) <= 0) {
                angle = 90;
                this.pontCPU++;

                if (this.pontCPU == 10) {
                    pontCPU = 0;
                    pontPlayer = 0;
                }
            } else if (Math.ceil(positionRight) >= this.screenWidth) {
                angle = -90;
                this.pontPlayer++;

                if (this.pontPlayer == 10) {
                    pontCPU = 0;
                    pontPlayer = 0;
                }
            }

            positionLeft = ((this.screenWidth / 2.0) - 25);
            positionTop = ((this.screenHeight / 2.0) - 25);
            positionRight = ((this.screenWidth / 2.0) + 25);
            positionBottom = ((this.screenHeight / 2.0) + 25);

            positionX = screenWidth / 2.0;
            positionY = screenHeight / 2.0;
        }

        // Bateu no chão ou no teto
        if (Math.ceil(positionBottom) >= Math.ceil(screenHeight) - 20 || Math.ceil(positionTop) <= 20) {
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
        } else if (Math.ceil(positionRight) >= screenWidth - 100 && ((positionTop + 50 >= enemy.getPositionTop() && positionTop <= enemy.getPositionBottom()) || (enemy.getPositionTop() + 50 <= positionBottom && enemy.getPositionTop() >= positionTop))) {
            comecou = true;
            double middlePlayer = enemy.getPositionTop() + 100;
            double distanceFromCenter = -Math.abs(middlePlayer - positionY);

            angle = 0.35 * distanceFromCenter;

            double aleatory = Math.random();
            inverte = aleatory <= 0.5;
        }

        if (angle > 0) {
            positionX += 30;
            if (comecou) {
                if (inverte) {
                    positionY -= (30.0 / Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                } else {
                    positionY += (30.0 / Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                }
            }
        } else {
            positionX -= 30;
            if (comecou) {
                if (inverte) {
                    positionY -= (30.0 / Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                } else {
                    positionY += (30.0 / Math.sin(Math.toRadians(90.0))) * Math.sin(Math.toRadians(angle));
                }
            }
        }


        if (positionY - 100 <= 20) {
            enemy.setPosition(positionX,120);
        } else if (positionY + 100 >= this.screenHeight - 20) {
            enemy.setPosition(positionX,this.screenHeight - 120);
        } else {
            enemy.setPosition(positionX,positionY);
        }

        // Bola anda
        positionLeft = positionX - 25;
        positionRight = positionX + 25;
        positionTop = positionY - 25;
        positionBottom = positionY + 25;

        canvas.drawRect((float) positionLeft, (float) positionTop, (float) positionRight, (float) positionBottom, paint);
        canvas.drawText(Integer.toString(pontPlayer), (float)(screenWidth/2) - 100, (float) 100.0, paint);
        canvas.drawText(Integer.toString(pontCPU), (float)(screenWidth/2) + 100, (float) 100.0, paint);
    }

    public void update() {
    }
}
