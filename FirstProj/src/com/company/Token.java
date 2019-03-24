package com.company;


public class Token {
    String word;
    int row=0;
    int col=0;
    int NestedBlockNumber;
    Symbols symbol;

    public void addToken(String word , int row , int col ){
        this.word=word;
        symbol=Lexer.isKeyWord(word);
        this.row=row;
        this.col=col;




    }
    public void setSymbol(Symbols symbol){
        this.symbol=symbol;
    }

    @Override
    public String toString() {
        return "the word is : "+ word +"\nthe Symbol Of it : " + symbol + "\n" + "row : "+ row +"\n" + "col : "+ col +"\n" ;
    }
}
