package br.unicamp.jogo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

/**
 * Main activity is the entry point to our application
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set window to fullscreen (will hide status bar)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = this.getWindow();
        window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
        );

        getSupportActionBar().hide();

        // Set content view to game, so that objects in the Game class can be rendered to the screen
        setContentView(new Game(this));
    }
}