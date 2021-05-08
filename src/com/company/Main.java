package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;


public class Main implements ActionListener {

    private static JButton obliczButton;
    private static JLabel plecakLabel;
    private static JTextField plecakText;
    private static JLabel wartoscLabel;
    private static JTextField wartoscText;
    private static JLabel wagaLabel;
    private static JTextField wagaText;
    private static JLabel wynikLabel;


    // ilosc wprowadzonych danych
    private int iloscDanych(String src)
    {
        // okreslenie ilosci danych
        int SIZE = 0;
        for (int i = 0; i < src.length(); i++)
        {
            if (src.charAt(i) == ' ')
                SIZE++;
        }
        return SIZE;
    }

    private void zapiszDoTablicy(String src, int [] tab, int tab_size)
    {
        String temp="";
        int index = 0;

        for (int i =0; i< src.length();i++)
        {
            if(src.charAt(i) == ' ') {
                tab[index] = Integer.parseInt(temp);
                index++;
                temp = "";
            }
            else
            {
                temp += src.charAt(i);
            }
        }
    }

    //algorytm bierze po kolei najwieksze wartosci dopoki mieszcza sie w plecaku
    private void Algorytm(int _pojemnosc, int [] _wartosc, int [] _waga, int _size)
    {


        int [] kolejnosc = new int[_size];
        for (int i = 0; i < _size; i++)
        {
            kolejnosc[i] = 999999;
        }

        int pojemnosc_obecna = 0;
        // sortujemy wg najwiekszych wartosci do najmniejszych
        for (int i = 0; i < _size-1; i++)
            for (int j = 0; j < _size-i-1; j++)
                if (_wartosc[j] < _wartosc[j+1])
                {
                    int temp = _wartosc[j];
                    _wartosc[j] = _wartosc[j+1];
                    _wartosc[j+1] = temp;

                    int temp1 = _waga[j];
                    _waga[j] = _waga[j+1];
                    _waga[j+1] = temp1;
                }


        // sprawdzam czy miesci sie w plecaku i zapisuje indeks wybranego przedmiotu
        int index = 0;
        for (int i = 0; i < _size; i++)
        {
            if(pojemnosc_obecna + _waga[i] <= _pojemnosc)
            {
                kolejnosc[index] = i;
                index++;
                pojemnosc_obecna += _waga[i];
            }
        }

        String wybrane = "Wybierz: ";

        for (int i = 0; i < _size; i++)
        {
            if(kolejnosc[i] != 999999){
                wybrane += String.valueOf(_wartosc[kolejnosc[i]]);
                System.out.print("(");
                wybrane += "(";
                wybrane += String.valueOf(_waga[kolejnosc[i]]);
                wybrane += ") ";
            }
        }
        wynikLabel.setText(wybrane);
    }



    public static void main(String[] args) {

	    JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setSize(600,400);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setTitle("Problem plecakowy");

	    panel.setLayout(null);

	    plecakLabel = new JLabel("Pojemność plecaka");
	    plecakLabel.setBounds(10,20,165,25);
	    panel.add(plecakLabel);

	    plecakText = new JTextField(20);
	    plecakText.setBounds(180,20,165,25);
	    panel.add(plecakText);

        wartoscLabel = new JLabel("Podaj wartość przedmiotów");
        wartoscLabel.setBounds(10,80,165,25);
        panel.add(wartoscLabel);

        wartoscText = new JTextField(20);
        wartoscText.setBounds(180,80,165,25);
        panel.add(wartoscText);

        wagaLabel = new JLabel("Podaj wagę przedmiotów");
        wagaLabel.setBounds(10,140,165,25);
        panel.add(wagaLabel);

        wagaText = new JTextField(20);
        wagaText.setBounds(180,140,165,25);
        panel.add(wagaText);

        obliczButton = new JButton("Oblicz");
        obliczButton.setBounds(180,200,80,25);
        panel.add(obliczButton);
        obliczButton.addActionListener(new Main());

        wynikLabel = new JLabel("");
        wynikLabel.setBounds(10,300,590,50);
        panel.add(wynikLabel);

        frame.setVisible(true);
    }


    // dzialanie przycisku
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if(source == obliczButton)
        {
            int plecak = Integer.parseInt(plecakText.getText().toString());
            String wartosc = wartoscText.getText().toString();
            wartosc += " ";
            String waga = wagaText.getText().toString();
            waga += " ";

            // ilosc danych wprowadzonych w polu wartosci przedmiotow (musi byc tyle samo co w wadze)
            int SIZE = iloscDanych(wartosc);

            // wpisanie liczb do tablicy z int'ami
            int [] wartoscArr = new int [SIZE];
            zapiszDoTablicy(wartosc,wartoscArr,SIZE);

            // rozmiar wag i zapis do tablicy
            int SIZE2 = iloscDanych(waga);
            int [] wagaArr = new int [SIZE2];
            zapiszDoTablicy(waga,wagaArr,SIZE2);

            // jesli wprowadzono rozne ilosci wag i wartosci
            if (SIZE != SIZE2)
            {
                wynikLabel.setText("Wprowadzono niepoprawne dane (ilosc wartosci przedmiotu oraz ilosc wag jest rozna)!");
            }

            // problem plecakowy
            Algorytm(plecak,wartoscArr,wagaArr,SIZE);

        }
    }
}
