package com.company;

import com.company.pieces.Piece;


public class Main
{
    public static void main(String[] args) {

        // Chess
        Chess.getInstance();
    }

    //Do not make alterations to this method!
    public static void print(Piece[][] array)
    {
        for(Piece[] row: array)
        {
            for(Piece thing: row)
            {
                System.out.print(thing + "\t");
            }
            System.out.println();
        }
    }
}