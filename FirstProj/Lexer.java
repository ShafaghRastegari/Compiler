import java.util.ArrayList;

/**
 * Created by M.H.GH.K on 10/13/2018.
 */

public class Lexer {
    ArrayList<Token> allOfTokens=new ArrayList<Token>();
    int row=1;
    int col=-1;
    int realCol=-1;
    int state=0;
    int length=0;
    char lastchar='\0';
    char nextChar;
    char[] word=new char[80];
    static int indexOfText=0;
    boolean newword=true;



    public void lexerAnalyzer(String text){
        while(text.length()+1!=indexOfText){
            if(newword){
                realCol=col+1;
                if(lastchar!='\0'){
                    realCol=realCol-1;
                }
                //col=col+1;
                Token newToken=new Token();
                word=new char[80];
                newword=false;
            }

            //nextInt=Integer.parseInt(String.valueOf(nextChar));
            if(lastchar!='\0'){
                nextChar=lastchar;
                lastchar='\0';


            }
            else {
                col=col+1;
                if(text.length()==indexOfText){
                    if(word[0]!='\0'){
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,col);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                    }
                    break;
                }
                //System.out.println(nextChar);

                nextChar=text.charAt(indexOfText);
                //System.out.println(nextChar);
                indexOfText++;
            }
            word[length++]=nextChar;

            switch (state){
                case 0:
                    if(nextChar=='\n'){
                        row=row+1;
                        col=-1;
                        realCol=-1;
                        newword=true;
                        break;
                    }
                    if(Character.isLetter(nextChar)){
                        //System.out.println("I am nextChar:");
                        //System.out.println(nextChar);
                        state=1;
                        break;
                    }
                    if(Character.isDigit(nextChar)){
                        //System.out.println("I am nextChar:");
                        //System.out.println(nextChar);
                        state=5;
                        break;
                    }
                    if(nextChar=='.'){
                        state=6;
                        break;

                    }
                    if(nextChar=='(' | nextChar==')' | nextChar=='+' | nextChar=='-' | nextChar==':' | nextChar==';' | nextChar==','){
                        state=0;
                        length=0;
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        newword=true;
                        break;

                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        length=0;
                        newword=true;
                        break;
                    }
                    if(nextChar=='='){
                        state=2;
                        break;
                    }
                    if(nextChar=='*'){
                        state=3;
                        break;
                    }
                    if(nextChar=='/'){
                        state=4;
                        break;
                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                    else {
                        word[length-1]='\0';
                        state=0;
                        length=0;
                        newword=true;

                        LexicalError(nextChar,col,realCol);

                        break;
                    }
                case 1:
                    if(Character.isLetter(nextChar) | Character.isDigit(nextChar) |nextChar=='_' ) {
                        //System.out.println("I am nextChar:");
                        //System.out.println(nextChar);
                        break;
                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                    else {
                        lastchar=nextChar;
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }


                case 2:
                    if(nextChar=='='){

                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;

                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }

                    else {
                        state=100;
                    }
                case 3:
                    if(nextChar=='*'){

                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;

                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                    else {
                        state=100;
                    }


                case 4:
                    if(nextChar=='/'){
                        Token newToken=new Token();
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;

                    }
                    else {
                        state=100;
                    }
                case 5:
                    if(Character.isDigit(nextChar)) {
                        //System.out.println("I am nextChar:");
                        //System.out.println(nextChar);
                        break;
                    }
                    if(nextChar=='.'){
                        state=6;
                        break;
                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.setSymbol(Symbols.S_number);
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                    else {
                        lastchar=nextChar;
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.setSymbol(Symbols.S_number);
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                case 6:
                    if(Character.isDigit(nextChar)) {
                        state=7;
                        break;
                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.setSymbol(Symbols.S_number);
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                    else {
                        lastchar=nextChar;
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.setSymbol(Symbols.S_number);
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }

                case 7:
                    if(Character.isDigit(nextChar)) {
                        break;
                    }
                    if(nextChar==' '){
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.setSymbol(Symbols.S_number);
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }
                    else {
                        lastchar=nextChar;
                        word[length-1]='\0';
                        Token newToken=new Token();
                        newToken.setSymbol(Symbols.S_number);
                        newToken.addToken(new String(word),row,realCol);
                        allOfTokens.add(newToken);
                        state=0;
                        length=0;
                        newword=true;
                        break;
                    }

                case 100:
                    lastchar=nextChar;
                    word[length-1]='\0';
                    Token newToken=new Token();
                    newToken.addToken(new String(word),row,realCol);
                    allOfTokens.add(newToken);
                    state=0;
                    length=0;
                    newword=true;




            }

        }


    }
    public static Symbols isKeyWord(String word){
        Symbols example;
        if(word.charAt(0)=='i' & word.charAt(1)=='f' ){
            example=Symbols.S_if;
            //System.out.print(example);
            return example;
        }
        else if(word.charAt(0)=='w' & word.charAt(1)=='h' & word.charAt(2)=='i' & word.charAt(3)=='l' & word.charAt(4)=='e'){
            example=Symbols.S_while;
            return example;
        }
        else if(word.charAt(0)=='d' & word.charAt(1)=='o'){
            example=Symbols.S_do;
            return example;
        }
        else if(word.charAt(0)=='f' & word.charAt(1)=='o' & word.charAt(2)=='r'){
            example=Symbols.S_for;
            return example;
        }
        else if(word.charAt(0)=='e' & word.charAt(1)=='l' & word.charAt(2)=='s' & word.charAt(3)=='e'){
            example=Symbols.S_else;
            return example;
        }
        else if(word.charAt(0)=='=' & word.charAt(1)=='='){
            example=Symbols.S_equal;
            return example;
        }
        else if(word.charAt(0)=='='){
            example=Symbols.S_assign;
            return example;
        }
        else if(word.charAt(0)=='!' & word.charAt(1)=='='){
            example=Symbols.S_notEqual;
            return example;
        }


        else if(Character.isDigit(word.charAt(0))) {
            example=Symbols.S_Number;
            return example;
        }
        else if(word.charAt(0)=='.'){
            if(Character.isDigit(word.charAt(1))){
                example=Symbols.S_Number;
                return example;
            }
            example=Symbols.S_dot;
            return example;
        }
        else if(word.charAt(0)=='('){
            example=Symbols.S_openParantez;
            return example;
        }
        else if(word.charAt(0)==')'){
            example=Symbols.S_closeParantez;
            return example;
        }
        else if(word.charAt(0)=='+'){
            example=Symbols.S_plus;
            return example;
        }
        else if(word.charAt(0)=='-'){
            example=Symbols.S_sub;
            return example;
        }
        else if(word.charAt(0)=='*' & word.charAt(1)=='*'){
            example=Symbols.S_power;
            return example;
        }
        else if(word.charAt(0)=='*'){
            example=Symbols.S_mul;
            return example;
        }
        else if(word.charAt(0)=='/' & word.charAt(1)=='/'){
            example=Symbols.S_devide;
            return example;
        }
        else if(word.charAt(0)=='/'){
            example=Symbols.S_correct_devide;
            return example;
        }

        else if(word.charAt(0)=='c' & word.charAt(1)=='a' & word.charAt(2)=='s' & word.charAt(3)=='e'){
            example=Symbols.S_case;
            return example;
        }
        else if(word.charAt(0)=='s' & word.charAt(1)=='w' & word.charAt(2)=='i' & word.charAt(3)=='t' & word.charAt(4)=='c' & word.charAt(5)=='h' ){
            example=Symbols.S_switch;
            return example;
        }
        else if(word.charAt(0)=='b' & word.charAt(1)=='r' & word.charAt(2)=='e' & word.charAt(3)=='a' & word.charAt(4)=='k'){
            example=Symbols.S_break;
            return example;
        }
        else if(word.charAt(0)=='c' & word.charAt(1)=='o' & word.charAt(2)=='n' & word.charAt(3)=='t' & word.charAt(4)=='i' & word.charAt(5)=='n' & word.charAt(6)=='u' & word.charAt(7)=='e'){
            example=Symbols.S_continue;
            return example;
        }
        else if(word.charAt(0)=='r' & word.charAt(1)=='e' & word.charAt(2)=='t' & word.charAt(3)=='u' & word.charAt(4)=='r' & word.charAt(5)=='n'){
            example=Symbols.S_return;
            return example;
        }
        else if(word.charAt(0)=='a' & word.charAt(1)=='n' & word.charAt(2)=='d'){
            example=Symbols.S_and;
            return example;
        }
        else if(word.charAt(0)=='o' & word.charAt(1)=='r'){
            example=Symbols.S_or;
            return example;
        }
        else if(word.charAt(0)==','){
            example=Symbols.S_comma;
            return example;

        }
        else if(word.charAt(0)==':'){
            example=Symbols.S_colon;
            return example;

        }
        else if(word.charAt(0)==';'){
            example=Symbols.S_semiColon;
            return example;

        }

        else if(word.charAt(0)=='.'){
            example=Symbols.S_dot;
            return example;
        }
        else {
            example=Symbols.S_id;
            return example;        }

    }


    public void Print() {
        for (Token theToken: allOfTokens){
            System.out.println(theToken);
        }

    }
    public void  LexicalError(char nextChar,int col,int row){
        System.out.println("**********************");
        System.out.printf("It Has Lexical Error in row: %d     col= %d\n",row,col);
        System.out.printf("the char is:%s\n",nextChar);
        System.out.println("**********************");



    }
}
