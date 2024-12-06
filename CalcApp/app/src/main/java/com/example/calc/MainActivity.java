package com.example.calc;
import androidx.appcompat.app.AppCompatActivity;
import   org.mariuszgromada.math.mxparser.*;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textView);
        display.setShowSoftInputOnFocus(false);
    }
    private void updateText(String strToAdd)
    {
        String oldStr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);
        display.setText(String.format("%s%s%S",leftStr,strToAdd,rightStr));
        display.setSelection(cursorPos + 1);
    }
    public void zeroBtn(View view) {
        updateText("0");

    }
    public void oneBtn(View view) {
        updateText("1");
    }
    public void twoBtn(View view) {
        updateText("2");
    }
    public void threeBtn(View view) {
        updateText("3");
    }
    public void fourBtn(View view) {
        updateText("4");
    }
    public void fiveBtn(View view) {
        updateText("5");
    }
    public void sixBtn(View view) {
        updateText("6");
    }
    public void sevenBtn(View view) {
        updateText("7");
    }
    public void eightBtn(View view) {
        updateText("8");
    }
    public void nineBtn(View view) {
        updateText("9");
    }
    public void pointBtn(View view) {
        updateText(".");
    }
    public void clearBtn(View view) {
        display.setText("");
    }
    public void addBtn(View view) {
        updateText("+");
    }
    public void subBtn(View view) {
        updateText("-");
    }
    public void mulBtn(View view) {
        updateText("×");
    }
    public void divBtn(View view) {
        updateText("÷");
    }
    public void plusminBtn(View view) {
        updateText("-");
    }
    public void expoBtn(View view) {
        updateText("^");
    }
    public void paraBtn(View view) {
        int cursorPos=display.getSelectionStart();
        int openPar = 0;
        int closePar = 0;
        int textLen = display.getText().length();

        for (int i=0;i<cursorPos;i++)
        {
            if(display.getText().toString().substring(i,i+1).equals("("))
            {
                openPar+=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")"))
            {
                closePar+=1;
            }
        }
        if(openPar==closePar || display.getText().toString().substring(textLen-1,textLen).equals("("))
        {
            updateText("(");
            display.setSelection(cursorPos + 1);
        }
        else if(closePar< openPar && !display.getText().toString().substring(textLen-1,textLen).equals("("))
        {
            updateText(")");

        }
        display.setSelection(cursorPos + 1);
    }
    public void delBtn(View view) {
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursorPos!=0 && textLen!=0)
        {
            SpannableStringBuilder selectio= (SpannableStringBuilder) display.getText();
            selectio.replace(cursorPos -1 ,cursorPos , "");
            display.setText(selectio);
            display.setSelection(cursorPos -1);
        }
    }
    public void equalBtn(View view) {
        String userExp=display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");
        Expression exp=new Expression(userExp);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());
    }


}