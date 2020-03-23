package br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.R;
import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.constants.Constantes;
import br.edu.ifsp.arq.dmos5_2020s1.calculadoradmo.model.Calculadora;

public class CalculadoraActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView txtViewvalor;
    private String valor;
    private String resultado;
    private String operador;
    private int operacao;
    private  Calculadora cal;

    //botões de operações
    private Button btnC;
    private Button btnCE;
    private Button btnDivisao;
    private Button btnMultiplicacao;
    private Button btnAdicao;
    private Button btnSubtracao;
    private Button btnIgual;
    private Button btnPotencial;
    //botoes de numeros;
    private Button btnZero;
    private Button btnUm;
    private Button btnDois;
    private Button btnTres;
    private Button btnQuatro;
    private Button btnCinco;
    private Button btnSeis;
    private Button btnSete;
    private Button btnOito;
    private Button btnNove;
    private Button btnPonto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        this.txtViewvalor = findViewById(R.id.textview_lcd);
        this.valor = "";
        this.initValores();
        this.initOperacoes();
        this.initOperador();

        this.cal = Calculadora.getInstance();
    }

    private void initValores(){
        this.resultado = "0";
        this.operacao = Constantes.NULO;
    }

    public void initOperacoes(){
        this.btnC = findViewById(R.id.button_c);
        this.btnDivisao = findViewById(R.id.button_divisao);
        this.btnMultiplicacao = findViewById(R.id.button_multiplicacao);
        this.btnAdicao = findViewById(R.id.button_adicao);
        this.btnSubtracao = findViewById(R.id.button_subtracao);
        this.btnIgual = findViewById(R.id.button_igual);
        this.btnPotencial = findViewById(R.id.button_potencia);
        this.btnCE = findViewById(R.id.button_ce);

        this.btnC.setOnClickListener(this);
        this.btnDivisao.setOnClickListener(this);
        this.btnMultiplicacao.setOnClickListener(this);
        this.btnAdicao.setOnClickListener(this);
        this.btnSubtracao.setOnClickListener(this);
        this.btnIgual.setOnClickListener(this);
        this.btnPotencial.setOnClickListener(this);
        this.btnCE.setOnClickListener(this);
    }

    public void initOperador() {
        this.btnZero = findViewById(R.id.button_zero);
        this.btnUm = findViewById(R.id.button_um);
        this.btnDois = findViewById(R.id.button_dois);
        this.btnTres = findViewById(R.id.button_tres);
        this.btnQuatro = findViewById(R.id.button_quatro);
        this.btnCinco = findViewById(R.id.button_cinco);
        this.btnSeis = findViewById(R.id.button_seis);
        this.btnSete = findViewById(R.id.button_sete);
        this.btnOito = findViewById(R.id.button_oito);
        this.btnNove = findViewById(R.id.button_nove);
        this.btnPonto = findViewById(R.id.button_ponto);

        this.btnZero.setOnClickListener(this);
        this.btnUm.setOnClickListener(this);
        this.btnDois.setOnClickListener(this);
        this.btnTres.setOnClickListener(this);
        this.btnQuatro.setOnClickListener(this);
        this.btnCinco.setOnClickListener(this);
        this.btnSeis.setOnClickListener(this);
        this.btnSete.setOnClickListener(this);
        this.btnOito.setOnClickListener(this);
        this.btnNove.setOnClickListener(this);
        this.btnPonto.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v == btnDivisao || v == btnMultiplicacao || v == btnAdicao
          || v == btnSubtracao || v == btnIgual || v == btnPotencial){
            this.operacoes(v);
        }else{
            this.operadores(v);
        }


    }

    private void operadores(View v){
        if(v == btnZero){
            this.valor += "0";
        }else if(v == btnUm){
            this.valor += "1";
        }else if(v == btnDois){
            this.valor += "2";
        }else if(v == btnTres){
            this.valor += "3";
        }else if(v == btnQuatro){
            this.valor += "4";
        }else if(v == btnCinco){
            this.valor += "5";
        }else if(v == btnSeis){
            this.valor += "6";
        }else if(v == btnSete){
            this.valor += "7";
        }else if(v == btnOito){
            this.valor += "8";
        }else if(v == btnNove){
            this.valor += "9";
        }else if(v == btnPonto){
            this.valor += ".";
        }else if(v == btnC){
            this.valor = "0";
            this.initValores();
            this.cal.c();
        }else if(v == btnCE){
            this.valor = "0";
        }
        this.mostrarResultado(Float.parseFloat(this.valor));
    }

    private void operacoes(View v){
        //verifico se é uma divisao e se o valor é igual a zero,
        //mostro uma mensagem de erro.
        if(this.operacao == Constantes.DIVISAO && Float.parseFloat(this.valor) == 0){
            Toast.makeText(this, "Entrada inválida", Toast.LENGTH_SHORT).show();
            this.cal.c();
            this.initValores();
            this.mostrarResultado(0);
        }else{
            if(v == btnDivisao){
                this.operacao = Constantes.DIVISAO;
            }else if(v == btnMultiplicacao){
                this.operacao = Constantes.MULTIPLICACAO;
            }else if(v == btnAdicao){
                this.operacao = Constantes.ADICAO;
            }else if(v == btnSubtracao){
                this.operacao = Constantes.SUBTRACAO;
            }else if(v == btnIgual){
                this.operacao = Constantes.RESULTADO;
            }else if(v == btnPotencial){
                this.operacao = Constantes.POTENCIA;
            }
            this.calcular();
        }

        this.valor = "0";
    }

    private void calcular(){
        float retorno;
        try{
            retorno = cal.calcular(this.operacao, Float.parseFloat(this.valor));
            System.out.println(retorno);
        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
            retorno = 0;
        }

        this.mostrarResultado(retorno);
    }

    private void mostrarResultado(float resultado){
        this.resultado = String.valueOf(resultado);
        this.txtViewvalor.setText(this.resultado);
    }
}
