# picocalculator
calculator for home work

# 課題
（問題３）
通常の数式（8桁までの整数値の四則演算のみ、括弧も使えるようにする）を
入力し計算結果を出力するプログラムをC++あるいはJavaにより作成せよ。

一般利用者が入力する可能性のある数式を出来る限り幅広く処理できるように仕様を明確に定義した上で、
数式エラー時には分かりやすいエラーメッセージを出力できるようにすること。

# 実装方針
BNF記法での文法定義を行い、文法定義に従った
- Lexerは簡易的なものを自作した
- デザインパターンInterpreterを使用する
- 扱う数値は8桁の数値なので(2147483648)で十分だが、将来扱える型を拡張できるようgenericsを使用する
- 演算子を表現するtokenの実装をFunctionオブジェクトを使用した。
  これによって各型をoperationするtokenを実装しなくてよい
  (Lexerにてtoken作成時にFunctionオブジェクトで演算処理を渡すことにしたため)

BigDecimalの拡張を実装した。

## BNF

    level0: <extpression> ::= <term> { + <term> | - <term> }*
    level1: <term> ::= <factor> { * <factor> | / <factor> }*
    level2: <factor> ::= <literal> | ( <extpression> )

上記BNFの左辺をExpressionクラスとして実装する。
各演算子はgenericなTokenクラスとして実装する。

# 実行方法

## 実行環境

Java SE 8

## ビルド方法

Antのビルドファイル「build。xml」を用意してあるため、これを使用する。
Antコマンドを導入後、プロジェクトディレクトリで下記コマンドを実行する。

    ant

## 起動方法

JavaSE-1.8のランタイムがインストールされている環境のプロジェクトディレクトリで、
下記コマンドを実行する。

    java -jar ./bin/PicoCalculator.jar

起動すると「式を入力してください。(「.」のみで終了します)」というプロンプトが表示されるので、
式を入力してエンターで計算結果を出力する。

「.」のみ入力してエンターで終了する。

- --debugでデバッグ出力あり。逆ポーランド記法での構文解析結果が得られる
- --factory:[ファクトリクラス名]で使用するファクトリを変更できる
  - SimpleCalculatorFactory 8桁までの整数のみ
  - BigDecimalCalculatorFactory BigDecimalを使用した、可変精度の電卓

# ソースコードの説明

各クラスごとの説明はjavadocを参照すること。

## PicoCalculatorの説明

コマンドラインで起動するMainクラス。
電卓のUIの実装部分。

## Factory類の説明

本プログラムはAbstractFactoryで構文(Lexer)および文法(Paser)を切り替えられるようになっている。

- AbstractCalcluatorFactory

計算機を作成するFactory。
SimpleCalcuratorFactoryは問題3で出題された、8桁までの整数のみ、四則演算のみの計算機を作成する。

- AbstractParserFactory

文法解析のためのParser(InterpreterパターンではExpression)オブジェクトを作成するためのFactory。
文法のレベルにあわせて、各文法レベルで使用するExpressionオブジェクトを生成できるようにしてある。
これによって文法解析の拡張時に既存Expressionを流用可能となるように考慮してある。
たとえば、変数をサポートした文法に対応するには下記の文法に従って、statementのレベルを追加、
factorの変数サポートを追加するだけで、簡易に変数をサポートした実装が可能なように考慮してある。
(変数の保持をどこでするかという設計問題が残っている)

    level0: <statement> ::= <variable> = <expression> | <expression>
    level1: <extpression> ::= <term> { + <term> | - <term> }*
    level2: <term> ::= <factor> { * <factor> | / <factor> }*
    level3: <factor> ::= <literal> | <variable> | ( <extpression> )

上記のAbstractFactoryパターンにより、
BigDecimalをサポートたLexerと、変数代入をサポートしたParserなどを組み合わせて機能拡張が可能になっている。
(他のLexerやParserは未実装だが、簡単に実装できるように工夫してある)
コマンドライン引数などで動作モードを簡単に変える(使うFactoryを変えればよい)などが可能になっている。

- AbstractTokenFactory

Tokenを生成するFactory。Lexerからトークンの文字列を渡されると、Tokenオブジェクトを生成する。
Lexerから使用する。

## expressionsパッケージの説明

expressionsにはinterpreterパターンでのexpressions、Parserの実装がパッケージされている。
BNF文法そのままのクラス名で、処理もBNF文法に従って実装している。
BNFを拡張した場合も同じ実装方針で実装すれば、拡張可能となっている。
また、構文解析時にデバッグを助けるため、逆ポーランド記法の構文解析結果を保存するようにしている。
BNF構文におけるlevelでFactoryからParserオブジェクトを作成できるようにしており、
基本的には構文木なので、一つしたの構文を呼び出すことを前提としている。
最後のレベルだけ、上位Parserを呼び出すため、+1したlevelをダミーレベルとして、必要な上位Parserを生成できるようにしている。

## tokensパッケージの説明

tokensには、Lexerの実装およびTokenの実装がパッケージされている。
interpreterパターンでは、ContextがLexerの役割を果たしているため、InterfaceとしてContextを定義してLexerを実装している。

## UnitTestの説明

コードカバレッジが100%になるようユニットテストを作成してある。
また、構文解析が正しく行われているか逆ポーランド記法による構文解析結果もチェックしている。
(これにより、たまたま答えの数字が合う/合わないだけでなく、正しく構文解析しているかもチェックできている)

