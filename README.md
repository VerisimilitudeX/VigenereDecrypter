###### For a more basic encrypter, please check out my [Caesar Cipher software auto decrypter](https://github.com/Verisimilitude11/Caesar-Cipher-Decrypter), which can automatically decrypt encrypted Caesar Ciphers without a key.

# VigenèreDecrypter

[![wakatime](https://wakatime.com/badge/github/Verisimilitude11/Vigenere-Cipher.svg)](https://wakatime.com/badge/github/Verisimilitude11/Vigenere-Cipher)

> Decrypt a Vigenère cipher in _*any*_ language without knowing the keys used for polyalphabetic substitution by performing frequency analysis and comparing categorical probability distributions.

The cipher, described in 1553 by Giovan Battista Bellaso, is simple to understand and implement, but it resisted all attempts to break it until 1863, three centuries later. This earned it the French description "le chiffrage indéchiffrable" (the indecipherable cipher).

This software decrypts a Vigenère cipher in *any* human language without knowing the keys used for polyalphabetic substitution.

The Caesar cipher is a type of substitution cipher in which each letter in the plaintext is 'shifted' down the alphabet a certain number of times.

Example: The text 'defend the east wall of the castle' is encrypted with a shift of 1 (key of 'a')
```
plaintext:  defend the east wall of the castle
ciphertext: efgfoe uif fbtu xbmm pg uif dbtumf
```
Decryption is just as simple; all it takes is a shift in the opposite direction.

A Vigenere is thus a series of intertwined Caesar ciphers. A key is used to encrypt and decrypt each cipher. The given keyword is repeated in a circular fashion until it matches the length of the plain text in order to generate a key. Each letter in the key represents the shift that the plaintext letter should receive.

Example: The text 'defend the east wall of the castle' is encrypted with a keyword of "fortify"
```
plaintext:  defend the east wall of the castle
key:        fortif yfo rtif yfor ti fyf ortify 
ciphertext: iswxvi rms vtay ufzc hn yfj qrlbqc
```

# Determining the Unknown Key:
To find the unknown key, this program employs a two-part strategy. The first section finds the key length using the Index of Coincidence, while the second part finds the actual key using the Chi-squared statistic.

## 1) Finding the Key Length:
The Vigenère uses various Caesar ciphers on successive letters. If we have a key of "dog," this will loop indefinitely until it matches the length of the ciphertext. Because our key length is three, the first letter of the ciphertext is decrypted with the same letter as the fourth letter ('d'). The same is true for the second and fifth letters ('o'). With a key length guess of 3, you get three letter sequences (letters 1,4,7,10... and 2,5,8,10... and 3,6,9,12...). So we'll divide our ciphertext into various sequences and measure their frequencies with the Index of Coincidence.

The Index of Coincidence (I.C.) is a statistical technique that determines how similar a piece of text is to English. Because I.C. is based on letter frequencies, the result remains unchanged when a substitution cipher is applied to the text. If the text is similar to English, the I.C. will be approximately 0.06; if the characters are evenly dispersed, the I.C. will be closer to 0.03-0.04. This procedure of guessing the key length and determining its I.C. is repeated.

Example: This ciphertext has a key length of 7, since it has the highest I.C. (14 is also high but that makes sense since the key is repeated in a cyclic manner).

```
length         avg I.C.
-----------------------
1 :     0.0449443523561
2 :     0.0457833618884
3 :     0.0435885364312
4 :     0.0474962292609
5 :     0.0393612078978
6 :     0.0471437059672
7 :     0.0909922589726 <--
8 :     0.0461858974359
9 :     0.0407804755631
10:     0.0361152882206
11:     0.0491603339901
12:     0.0512663398693
13:     0.0446886446886
14:     0.0988487702773 <--
15:     0.0334554334554
```

## 2) Finding the Key:
Given that the key length is 7, we essentially have 7 Caesar ciphers to break. To crack the code, we'll utilize the Chi-squared statistic, which is a method for comparing two frequency distributions. Our first sequence is 'VURZJUGRGGUGVGJQKEOAGUGKKQVWQP', which uses every seventh letter beginning with the first. We will attempt to decrypt this using all 26 letters of the English language and compare those letter frequencies with the English letter frequencies using the Chi-squared statistic. The result with the lowest statistic is the most similar to English.

Example: This shows us that using a shift of 2 will yield the result closest to English.  A shift of 2 corresponds to the letter 'c' (a = 0, b = 1, c = 2...).  So we know the first letter of the key is 'c'.  This process is repeated for the rest of the key's letters.
```
key         deciphered sequence           chi-sq
------------------------------------------------
0      VURZJUGRGGUGVGJQKEOAGUGKKQVWQP     595.42
1      UTQYITFQFFTFUFIPJDNZFTFJJPUVPO     466.86
2      TSPXHSEPEESETEHOICMYESEIIOTUON      41.22 <--
3      SROWGRDODDRDSDGNHBLXDRDHHNSTNM      67.73
4      RQNVFQCNCCQCRCFMGAKWCQCGGMRSML     642.37
5      QPMUEPBMBBPBQBELFZJVBPBFFLQRLK     451.49
6      POLTDOALAAOAPADKEYIUAOAEEKPQKJ     121.97
7      ONKSCNZKZZNZOZCJDXHTZNZDDJOPJI    2441.20
8      NMJRBMYJYYMYNYBICWGSYMYCCINOIH     190.46
9      MLIQALXIXXLXMXAHBVFRXLXBBHMNHG    1142.90
10     LKHPZKWHWWKWLWZGAUEQWKWAAGLMGF     358.87
11     KJGOYJVGVVJVKVYFZTDPVJVZZFKLFE     962.13
12     JIFNXIUFUUIUJUXEYSCOUIUYYEJKED     354.18
13     IHEMWHTETTHTITWDXRBNTHTXXDIJDC     241.97
14     HGDLVGSDSSGSHSVCWQAMSGSWWCHICB     107.36
15     GFCKUFRCRRFRGRUBVPZLRFRVVBGHBA     136.40
16     FEBJTEQBQQEQFQTAUOYKQEQUUAFGAZ    1801.65
17     EDAISDPAPPDPEPSZTNXJPDPTTZEFZY     531.22
18     DCZHRCOZOOCODORYSMWIOCOSSYDEYX     247.66
19     CBYGQBNYNNBNCNQXRLVHNBNRRXCDXW     377.60
20     BAXFPAMXMMAMBMPWQKUGMAMQQWBCWV     489.12
21     AZWEOZLWLLZLALOVPJTFLZLPPVABVU     815.45
22     ZYVDNYKVKKYKZKNUOISEKYKOOUZAUT     648.33
23     YXUCMXJUJJXJYJMTNHRDJXJNNTYZTS    1476.11
24     XWTBLWITIIWIXILSMGQCIWIMMSXYSR     279.93
25     WVSAKVHSHHVHWHKRLFPBHVHLLRWXRQ     158.53
```
