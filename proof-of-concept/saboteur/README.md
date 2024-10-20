https://sinclair.wiki.zxnet.co.uk/wiki/TR-DOS_filesystem
https://sinclair.wiki.zxnet.co.uk/wiki/Example_TR-DOS_Basic_file
http://fileformats.archiveteam.org/wiki/Sinclair_BASIC_tokenized_file

https://github.com/Loxrie/fuse-emulator-utils/blob/master/listbasic.c
https://retrocomputing.stackexchange.com/questions/5798/make-a-basic-tap-file-readable-on-linux

https://en.wikipedia.org/wiki/ZX_Spectrum_character_set
https://mdfs.net/Docs/Comp/Spectrum/SysVars
https://nukpage.narod.ru/zx/contact/books/trdosman.htm

https://github.com/Christofoletti/Z80-Hacker/blob/master/z80.hacker/src/main/resources/z80-instructions-extended.dat

0000001000: 00 0A 1C 00 E7 31 0E 00 │ 00 01 00 00 3A DA 31 0E   ◙∟ ç1♫  ☺  :Ú1♫                                      
0000001010: 00 00 01 00 00 3A FD B0 │ 22 32 34 37 39 39 22 0D    ☺  :ý°"24799"♪

00 0A -> 10
<line number> 2 byte    
1C 00 -> 16 + 12 = 28 
<length of this line>

E7 -> BORDER
0E -> <number> + 5 bytes
3A -> : -> A sentence is the simplest BASIC instruction (e.g. PRINT). 
Sentences might contain ''arguments'' and can be separated by a ''colon'' (:) or by ''end of line''.

DA -> PAPER ? <number + 5 bytes> :
FD -> CLEAR
B0 -> VAL

10 BORDER 1 <number> : PAPER 1 <number> : CLEAR VAL "24799"

0000001000:             E7 31 0E 00 │ 00 01 00 00 3A DA 31 0E   ◙∟ ç1♫  ☺  :Ú1♫                                      
0000001010: 00 00 01 00 00 3A FD B0 │ 22 32 34 37 39 39 22 0D   ☺  :ý°"24799"♪

???
E7 -> BORDER
DA -> PAPER
FD -> CLEAR
B0 -> VAL 

22 32 34 37 39 39 22
"24799"

0D
<new line>

result:
10 ???? "24799"

0000001020: 00 14 10 00 F4 B0 22 32 │ 33 37 33 39 22 2C 20 AF   ¶► ô°"23739", ¯                              
0000001030: 22 6F 22 0D             │                          "o"♪ ▲( ùÀ15619♫

F4 -> POKE
B0 -> VAL
AF -> CODE "o"

length: 16
result:
20 POKE VAL "23739", VAL CODE "o"

0000001030:             00 1E 28 00 │ F9 C0 31 35 36 31 39 0E  "o"♪ ▲( ùÀ15619♫                          
0000001040: 00 00 03 3D 00 3A EA 3A │ 4C 4F 41 44 20 22 73 32    ♥= :ê:LOAD "s2                 
0000001050: 73 63 72 22 20 43 4F 44 │ 45 20 33 32 37 36 38 0D  scr" CODE 32768♪  

F9 -> RANDOMIZE
C0 -> USR 15619 <number> : 
EA -> REM : LOAD "s2scr" CODE 32768

length = 0x28
30 RANDOMIZE USR 15619 <number> : REM : LOAD "s2scr" CODE 32768

0000001060: 00 28 0E 00 F9 C0 33 32 │ 37 36 38 0E 00 00 00 80   (♫ ùÀ32768♫   €                    
0000001070: 00 0D                   │                           ♪ 2) ùÀ15619♫

F9 -> RANDOMIZE
C0 -> USR

40 RANDOMIZE USR 32768 <number>

0000001070:       00 32 29 00 F9 C0 │ 31 35 36 31 39 0E 00 00   ♪ 2) ùÀ15619♫
0000001080: 03 3D 00 3A EA 3A 4C 4F │ 41 44 20 22 73 32 6D 61  ♥= :ê:LOAD "s2ma                     
0000001090: 69 6E 22 20 43 4F 44 45 │ 20 32 34 38 30 30 0D     in" CODE 24800♪

?? RANDOMIZE USR 15619 : REM : LOAD "s2main" CODE 24800

0000001090:                         │                      00  in" CODE 24800♪                      
00000010A0: 3C 12 00 F4 B0 22 32 33 │ 37 33 39 22 2C 20 B0 22  <↕ ô°"23739", °"                      
00000010B0: 32 34 34 22 0D          │                          244"♪ F♫ ùÀ24800

00000010B0:                00 46 0E │ 00 F9 C0 32 34 38 30 30  244"♪ F♫ ùÀ24800
00000010C0: 0E 00 00 E0 60 00 0D    │                          ♫  à` ♪€ª◙ 5619♫

?? RANDOMIZE USR 24800 <number> 

00000010C0:                      80 │ AA 0A 00 35 36 31 39 0E  ♫  à` ♪€ª◙ 5619♫                   
00000010D0: 00 00 03 3D 00 3A EA 3A │ F8 20 22 53 41 42 32 22    ♥= :ê:ø "SAB2"                    
00000010E0: CA 20 31 30 0D          │ 5F 04 00 00 00 17 5F 04  Ê 10♪€Ê↨_♦   ↨_♦

00000010E0:                80 CA 17 │ 5F 04 00 00 00 17 5F 04  Ê 10♪€Ê↨_♦   ↨_♦                    
00000010F0: 00 00 00 0A 00 00 00 00 │ 00 00 00 00 00 00 00 00     ◙            