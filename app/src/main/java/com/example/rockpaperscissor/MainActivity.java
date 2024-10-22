package com.example.rockpaperscissor;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ImageView playerImg;
    private ImageView computerImg;

    private Button rockBtn;
    private Button paperBtn;
    private Button scissorBtn;

    private TextView playerScoreText;
    private TextView computerScoreText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
        rockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerImg.setImageResource(R.drawable.rock);
                GameLoop(0);
            }
        });
        paperBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerImg.setImageResource(R.drawable.paper);
                GameLoop(1);
            }
        });
        scissorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerImg.setImageResource(R.drawable.scissors);
                GameLoop(2);
            }
        });
    }
    private int RandomChoice() {
        Random rnd = new Random();
        return rnd.nextInt(3);
    }

    private int playerScore = 0;
    private int computerScore = 0;

    private void test(int playerChoiceId, int computerChoiceId){
        if (playerChoiceId != computerChoiceId){
            if (
                    playerChoiceId == 0 && computerChoiceId == 1
                    || playerChoiceId == 1 && computerChoiceId == 2
                    || playerChoiceId == 2 && computerChoiceId == 0
            ){
                computerScore++;
                computerScoreText.setText("Computer: " + computerScore);

                Toast.makeText(MainActivity.this, "Computer Win!",
                        Toast.LENGTH_SHORT).show();
            }
            else{
                playerScore++;
                playerScoreText.setText("Player: " + playerScore);

                Toast.makeText(MainActivity.this, "Player Win!",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(MainActivity.this, "Draw!",
                    Toast.LENGTH_SHORT).show();
        }
    }
    public void GameLoop(int playerChoice) {
        int computerChoice = RandomChoice();
        switch (computerChoice){
            case 0:
                computerImg.setImageResource(R.drawable.rock);
                break;
            case 1:
                computerImg.setImageResource(R.drawable.paper);
                break;
            case 2:
                computerImg.setImageResource(R.drawable.scissors);
        }
        test(playerChoice, computerChoice);
        if (playerScore == 3){
            CreateAlert("Win");
        }
        if (computerScore == 3) {
            CreateAlert("Lose");
        }
    }

    public void CreateAlert(String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

        builder.setMessage("Do you want to play another Game?");
        builder.setTitle(title);
        builder.setCancelable(false);

        builder.setPositiveButton("Yes", (DialogInterface.OnClickListener) (dialog, which) -> {
            playerScore = 0;
            computerScore = 0;
            playerScoreText.setText("Player: " + playerScore);
            computerScoreText.setText("Computer: " + computerScore);
            dialog.cancel();
        });

        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            finish();
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void init() {
        playerImg = findViewById(R.id.playerImg);
        computerImg = findViewById(R.id.computerImg);
        rockBtn = findViewById(R.id.rockBtn);
        paperBtn = findViewById(R.id.paperBtn);
        scissorBtn = findViewById(R.id.scissorBtn);
        computerScoreText = findViewById(R.id.computerScore);
        playerScoreText = findViewById(R.id.playerScore);
    }
}