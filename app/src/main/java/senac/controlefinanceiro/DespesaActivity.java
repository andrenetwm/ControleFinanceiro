package senac.controlefinanceiro;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import senac.controlefinanceiro.objects.Despesa;

public class DespesaActivity extends AppCompatActivity {

    EditText valorDespesa;
    EditText dataDespesa;
    EditText descricaoDespesa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        valorDespesa = findViewById(R.id.valor_despesa);
        dataDespesa = findViewById(R.id.data_despesa);
        descricaoDespesa = findViewById(R.id.descricao_despesa);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_despesa, menu);

        return true;
    }

    public void calendario(View view) {
        try {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            dataDespesa.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        } catch (Exception e){
            Log.e("Despesa", e.getMessage());
        }
    }

    public void salvar(View view) {
        try {

            if (valorDespesa.getText().toString().isEmpty()) {
                valorDespesa.setError("Entre com o valor");

                return;
            }

            Despesa despesa = new Despesa(
                    Double.parseDouble(valorDespesa.getText().toString()),
                    new SimpleDateFormat("dd-MM-yyyy").parse(dataDespesa.getText().toString()),
                    descricaoDespesa.getText().toString()
            );

            ListActivity.contas.add(despesa);

            finish();

        } catch (Exception e){
            Log.e("Despesa", e.getMessage());
        }
    }
}
