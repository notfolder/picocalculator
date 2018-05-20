# picocalculator
calculator for home work

# 課題
（問題３）
通常の数式（8桁までの整数値の四則演算のみ、括弧も使えるようにする）を
入力し計算結果を出力するプログラムをC++あるいはJavaにより作成せよ.

一般利用者が入力する可能性のある数式を出来る限り幅広く処理できるように仕様を明確に定義した上で、
数式エラー時には分かりやすいエラーメッセージを出力できるようにすること.

# 実装方針
BNF記法での文法定義を行い、文法定義に従った
- LexerはStringTokenizerを使う
- デザインパターンInterpreterを使用する
- 扱う数値は8桁の数値なので(2147483648)で十分だが、将来扱える型を拡張できるようgenericsを使用する
- 演算子を表現するtokenの実装をFunctionオブジェクトを使用した.
  これによって各型をoperationするtokenを実装しなくてよい
  (Lexerにてtoken作成時にFunctionオブジェクトで演算処理を渡すことにしたため)

BigDecimalなどの拡張を想定している.

## BNF

    level0: <extpression> ::= <term> { + <term> | - <term> }*
    level1: <term> ::= <factor> { * <factor> | / <factor> }*
    level2: <factor> ::= <literal> | ( <extpression> )

上記BNFの左辺をExpressionクラスとして実装する.
各演算子はgenericなTokenクラスとして実装する.

# 実行方法

## 実行環境

Java SE 8

## ビルド方法

Antのビルドファイル「build.xml」を用意してあるため、これを使用する.
Antコマンドを導入後、プロジェクトディレクトリで下記コマンドを実行する.

    ant

## 起動方法

JavaSE-1.8のランタイムがインストールされている環境のプロジェクトディレクトリで、
下記コマンドを実行する.

    java -jar ./bin/PicoCalculator.jar

起動すると「式を入力してください.(「.」のみで終了します)」というプロンプトが表示されるので、
式を入力してエンターで計算結果を出力する.

「.」のみ入力してエンターで終了する.

# ソースコードの説明

javadocにて説明する.



