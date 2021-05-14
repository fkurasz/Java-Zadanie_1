package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class Main implements ActionListener {

    private static JButton obliczButton;
    private static JLabel plecakLabel;
    private static JTextField plecakText;
    private static JLabel wartoscLabel;
    private static JTextField wartoscText;
    private static JLabel wagaLabel;
    private static JTextField wagaText;
    private static JLabel wynikLabel;

    private static JButton obliczButtonRandom;
    private static JLabel seedRandom;
    private static JTextField seedTextRandom;
    private static JLabel plecakLabelRandom;
    private static JButton plecakButtonRandom;
    private static JLabel wartoscLabelRandom;
    private static JTextField wartoscTextRandom;
    private static JLabel wagaLabelRandom;
    private static JTextField wagaTextRandom;


    // ilosc wprowadzonych danych
    private int iloscDanych(String src)
    {
        int SIZE = 0;
        if(src.length()>0)
        {
            // okreslenie ilosci danych
            for (int i = 0; i < src.length(); i++)
            {
                if (src.charAt(i) == ' ')
                    SIZE++;
            }
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
            if(src.charAt(i) == ' ')
            {
                tab[index] = Integer.parseInt(temp);
                index++;
                temp = "";
            }
            else if(i == src.length()-1)
            {
                temp += src.charAt(i);
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
                wybrane += "(";
                wybrane += String.valueOf(_waga[kolejnosc[i]]);
                wybrane += ") ";
            }
        }
        wynikLabel.setText(wybrane);
    }



    public static void main(String[] args) {

        long liczba = 500;
        RandomNumberGenerator random = new RandomNumberGenerator(liczba);
        //int y = random.nextInt(1,101);
        //int x = ThreadLocalRandom.current().nextInt(1,101);
        //System.out.println(y);


	    JPanel panel = new JPanel();
	    JFrame frame = new JFrame();
	    frame.setSize(800,450);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.add(panel);
	    frame.setTitle("Problem plecakowy");

	    panel.setLayout(null);

	    // recznie
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

        //RANODOMOWO
        plecakLabelRandom = new JLabel("Losuj pojemność plecaka");
        plecakLabelRandom.setBounds(400,20,165,25);
        panel.add(plecakLabelRandom);

        plecakButtonRandom = new JButton("Losuj");
        plecakButtonRandom.setBounds(600,20,80,25);
        panel.add(plecakButtonRandom);
        plecakButtonRandom.addActionListener(new Main());

        wartoscLabelRandom = new JLabel("Liczba losowanych wartości");
        wartoscLabelRandom.setBounds(400,80,165,25);
        panel.add(wartoscLabelRandom);

        wartoscTextRandom = new JTextField(20);
        wartoscTextRandom.setBounds(600,80,40,25);
        panel.add(wartoscTextRandom);

        wagaLabelRandom = new JLabel("Liczba losowanych wag");
        wagaLabelRandom.setBounds(400,140,165,25);
        panel.add(wagaLabelRandom);

        wagaTextRandom = new JTextField(20);
        wagaTextRandom.setBounds(600,140,40,25);
        panel.add(wagaTextRandom);

        seedRandom = new JLabel("Podaj seed");
        seedRandom.setBounds(400,200,165,25);
        panel.add(seedRandom);

        seedTextRandom = new JTextField("500",20);
        seedTextRandom.setBounds(600,200,40,25);
        panel.add(seedTextRandom);

        obliczButtonRandom = new JButton("Losuj");
        obliczButtonRandom.setBounds(600,260,80,25);
        panel.add(obliczButtonRandom);
        obliczButtonRandom.addActionListener(new Main());
        // przycisk

        obliczButton = new JButton("Oblicz");
        obliczButton.setBounds(180,260,80,25);
        panel.add(obliczButton);
        obliczButton.addActionListener(new Main());

        wynikLabel = new JLabel("Wybierz:");
        wynikLabel.setBounds(10,350,790,50);
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
            String waga = wagaText.getText().toString();

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
                wynikLabel.setText("Wprowadzono niepoprawne dane (ilosc wartosci przedmiotu oraz ilosc wag jest rozna!)");
            }

            // problem plecakowy
            Algorytm(plecak,wartoscArr,wagaArr,SIZE);

            if(wynikLabel.getText() == "Wybierz: ")
            {
                wynikLabel.setText("Niczego nie zmiescisz do plecaka.");
            }
        }

        if(source == plecakButtonRandom)
        {
            int wynik = ThreadLocalRandom.current().nextInt(50,301);
            plecakText.setText(String.valueOf(wynik));
        }

        if(source == obliczButtonRandom)
        {
            int wartosc = 0;
            int waga = 0;
            int seed = 0;

            String temp = "";
            temp = wartoscTextRandom.getText().toString();
            if(temp.length() > 0)
            {
                wartosc = Integer.parseInt(wartoscTextRandom.getText().toString());
            }
            temp = wagaTextRandom.getText().toString();
            if(temp.length() > 0)
            {
                waga = Integer.parseInt(wagaTextRandom.getText().toString());
            }
            temp = seedTextRandom.getText().toString();
            if(temp.length() > 0)
            {
                seed = Integer.parseInt(seedTextRandom.getText().toString());
            }

            if(wartosc!=0 && waga!=0 && wartosc!=waga)
            {
                wynikLabel.setText("Wprowadzono niepoprawne dane (liczba losowanych wartości musi być taka sama jak liczba losowanych wag!)");
            }
            if(seed <= 0)
            {
                wynikLabel.setText("Wprowadzono niepoprawne dane (wartosc seed musi myc wieksza niz 0)");
            }

            if(wartosc >0)
            {
                // 1 pole tekstowe
                String wynik = "";
                int los = 0;
                for (int i = 0; i < wartosc; i++)
                {
                    los = ThreadLocalRandom.current().nextInt(1,301);
                    wynik += String.valueOf(los);
                    if(i != wartosc-1)
                        wynik+=" ";
                }
                wartoscText.setText(String.valueOf(wynik));
            }

            if(waga > 0)
            {
                // 2 pole tekstowe
                String wynik = "";
                int los = 0;
                for (int i = 0; i < waga; i++) {
                    los = ThreadLocalRandom.current().nextInt(1, 301);
                    wynik += String.valueOf(los);
                    if(i != wartosc-1)
                        wynik+=" ";
                }
                wagaText.setText(String.valueOf(wynik));
            }
        }
    }
}
