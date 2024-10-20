                    ld        de,$4000                      ;[8000] 11 00 40
                    ld        hl,$8084                      ;[8003] 21 84 80
                    ld        bc,$ffff                      ;[8006] 01 ff ff
                    ld        ($8034),bc                    ;[8009] ed 43 34 80 -> label_7 + 1
                    inc       bc                            ;[800d] 03
                    ld        a,$80                         ;[800e] 3e 80
                    jr        $803d                         ;[8010] 18 2b       -> label_5


:label_1            ld        c,$fe                         ;[8012] 0e fe
                    add       a                             ;[8014] 87
                    jp        nz,$801b                      ;[8015] c2 1b 80    -> label_6
                    ld        a,(hl)                        ;[8018] 7e
                    inc       hl                            ;[8019] 23
                    rla                                     ;[801a] 17
:label_6            call      nc,$805b                      ;[801b] d4 5b 80
                    inc       c                             ;[801e] 0c
                    ret       z                             ;[801f] c8
                    ld        b,c                           ;[8020] 41
                    ld        c,(hl)                        ;[8021] 4e
                    inc       hl                            ;[8022] 23
                    rr        b                             ;[8023] cb 18
                    rr        c                             ;[8025] cb 19
                    ld        ($8034),bc                    ;[8027] ed 43 34 80 -> label_7 + 1
                    ld        bc,$0001                      ;[802b] 01 01 00
                    call      nc,$805b                      ;[802e] d4 5b 80
                    inc       bc                            ;[8031] 03
:label_2            push      hl                            ;[8032] e5
:label_7            ld        hl,$0000                      ;[8033] 21 00 00    -> label_7
                    add       hl,de                         ;[8036] 19
                    ldir                                    ;[8037] ed b0
                    pop       hl                            ;[8039] e1
                    add       a                             ;[803a] 87
                    jr        c,$8012                       ;[803b] 38 d5       ->  label_1
:label_5            inc       c                             ;[803d] 0c
                    add       a                             ;[803e] 87
                    jp        nz,$8045                      ;[803f] c2 45 80    -> label_4
                    ld        a,(hl)                        ;[8042] 7e
                    inc       hl                            ;[8043] 23
                    rla                                     ;[8044] 17
:label_4            call      nc,$805b                      ;[8045] d4 5b 80
                    ldir                                    ;[8048] ed b0
                    add       a                             ;[804a] 87
                    jr        c,$8012                       ;[804b] 38 c5       ->  label_1
                    inc       c                             ;[804d] 0c
                    add       a                             ;[804e] 87
                    jp        nz,$8055                      ;[804f] c2 55 80    -> label_3
                    ld        a,(hl)                        ;[8052] 7e
                    inc       hl                            ;[8053] 23
                    rla                                     ;[8054] 17
:label_3            call      nc,$805b                      ;[8055] d4 5b 80
                    jp        $8032                         ;[8058] c3 32 80    -> label_2

; subprogram
                    add       a                             ;[805b] 87
                    rl        c                             ;[805c] cb 11
                    add       a                             ;[805e] 87
                    jr        nc,$805b                      ;[805f] 30 fa   -> subprogram
                    ret       nz                            ;[8061] c0
                    ld        a,(hl)                        ;[8062] 7e
                    inc       hl                            ;[8063] 23
                    rla                                     ;[8064] 17
                    ret       c                             ;[8065] d8

                    add       a                             ;[8066] 87
                    rl        c                             ;[8067] cb 11
                    add       a                             ;[8069] 87
                    ret       c                             ;[806a] d8

                    add       a                             ;[806b] 87
                    rl        c                             ;[806c] cb 11
                    add       a                             ;[806e] 87
                    ret       c                             ;[806f] d8

                    add       a                             ;[8070] 87
                    rl        c                             ;[8071] cb 11
                    add       a                             ;[8073] 87
                    ret       c                             ;[8074] d8

                    add       a                             ;[8075] 87
                    rl        c                             ;[8076] cb 11
                    rl        b                             ;[8078] cb 10
                    add       a                             ;[807a] 87
                    jr        nc,$8075                      ;[807b] 30 f8
                    ret       nz                            ;[807d] c0
                    ld        a,(hl)                        ;[807e] 7e
                    inc       hl                            ;[807f] 23
                    rla                                     ;[8080] 17
                    jr        nc,$8075                      ;[8081] 30 f2
                    ret                                     ;[8083] c9

; Look like a data
                    sub       l                             ;[8084] 95
                    rst       $38                           ;[8085] ff
                    ld        h,d                           ;[8086] 62
                    add       b                             ;[8087] 80
                    nop                                     ;[8088] 00
                    ld        h,(hl)                        ;[8089] 66
                    inc       bc                            ;[808a] 03
                    ret       m                             ;[808b] f8
                    nop                                     ;[808c] 00
                    dec       d                             ;[808d] 15
                    cp        (hl)                          ;[808e] be
                    ld        bc,$09c0                      ;[808f] 01 c0 09
                    dec       b                             ;[8092] 05
                    rst       $38                           ;[8093] ff
                    rst       $38                           ;[8094] ff
                    cp        $42                           ;[8095] fe 42
                    add       hl,bc                         ;[8097] 09
                    ld        (hl),$8e                      ;[8098] 36 8e
                    ld        (hl),c                        ;[809a] 71
                    rst       $38                           ;[809b] ff
                    ld        d,$18                         ;[809c] 16 18
                    ld        bc,$e7a1                      ;[809e] 01 a1 e7
                    rst       $00                           ;[80a1] c7
                    ld        a,a                           ;[80a2] 7f
                    inc       de                            ;[80a3] 13
                    or        $cd                           ;[80a4] f6 cd
                    ld        b,b                           ;[80a6] 40
                    ld        h,b                           ;[80a7] 60
                    ld        h,a                           ;[80a8] 67
                    ld        h,$63                         ;[80a9] 26 63
                    ld        h,a                           ;[80ab] 67
                    ex        af,af'                        ;[80ac] 08
                    dec       b                             ;[80ad] 05
                    adc       (ix-$01)                      ;[80ae] dd 8e ff
                    ret       c                             ;[80b1] d8
                    add       l                             ;[80b2] 85
                    ret       nz                            ;[80b3] c0
                    ld        e,c                           ;[80b4] 59
                    in        a,($17)                       ;[80b5] db 17
                    rst       $38                           ;[80b7] ff
                    ld        sp,hl                         ;[80b8] f9
                    rst       $38                           ;[80b9] ff
                    ret       nz                            ;[80ba] c0
                    pop       bc                            ;[80bb] c1
                    ld        b,b                           ;[80bc] 40
                    or        a                             ;[80bd] b7
                    ld        e,a                           ;[80be] 5f
                    ld        (hl),b                        ;[80bf] 70
                    cp        b                             ;[80c0] b8
                    ret       po                            ;[80c1] e0
                    add       b                             ;[80c2] 80
                    ld        l,e                           ;[80c3] 6b
                    push      de                            ;[80c4] d5
                    sbc       b                             ;[80c5] 98
                    rst       $38                           ;[80c6] ff
                    nop                                     ;[80c7] 00
                    dec       c                             ;[80c8] 0d
                    rst       $20                           ;[80c9] dd e7
                    ret       nz                            ;[80cb] c0
                    call      nz,$f6fe                      ;[80cc] c4 fe f6
                    ret       nz                            ;[80cf] c0
                    scf                                     ;[80d0] 37
                    ld        c,$ae                         ;[80d1] 0e ae
                    nop                                     ;[80d3] 00
                    ld        h,c                           ;[80d4] 61
                    and       b                             ;[80d5] a0
                    inc       a                             ;[80d6] 3c
                    ld        b,b                           ;[80d7] 40
                    ld        a,$42                         ;[80d8] 3e 42
                    ld        a,(hl)                        ;[80da] 7e
                    dec       h                             ;[80db] 25
                    cp        $3c                           ;[80dc] fe 3c
                    ld        b,d                           ;[80de] 42
                    ld        b,d                           ;[80df] 42
                    inc       a                             ;[80e0] 3c
                    ld        a,(hl)                        ;[80e1] 7e
                    ld        b,d                           ;[80e2] 42
                    ld        a,b                           ;[80e3] 78
                    and       l                             ;[80e4] a5
                    ld        b,$58                         ;[80e5] 06 58
                    ld        (hl),a                        ;[80e7] 77
                    ld        l,(hl)                        ;[80e8] 6e
                    cp        (hl)                          ;[80e9] be
                    ld        a,c                           ;[80ea] 79
                    ld        a,a                           ;[80eb] 7f
                    add       b                             ;[80ec] 80
                    ld        b,b                           ;[80ed] 40
                    ld        b,$1c                         ;[80ee] 06 1c
                    ld        bc,$e341                      ;[80f0] 01 41 e3
                    adc       a                             ;[80f3] 8f
                    cp        a                             ;[80f4] bf
                    push      bc                            ;[80f5] c5
                    add       b                             ;[80f6] 80
                    sbc       e                             ;[80f7] 9b
                    ccf                                     ;[80f8] 3f
                    ret       p                             ;[80f9] f0
                    inc       bc                            ;[80fa] 03
                    ld        (hl),b                        ;[80fb] 70
                    nop                                     ;[80fc] 00
                    adc       c                             ;[80fd] 89
                    ld        a,(bc)                        ;[80fe] dd dd 0a
                    dec       hl                            ;[8101] 2b
                    and       a                             ;[8102] a7
                    or        $c1                           ;[8103] f6 c1
                    ret       nz                            ;[8105] c0
                    adc       l                             ;[8106] 8d
                    ld        a,a                           ;[8107] 7f
                    ei                                      ;[8108] fb
                    rst       $08                           ;[8109] cf
                    nop                                     ;[810a] 00
                    cp        $5d                           ;[810b] fe 5d
                    sub       b                             ;[810d] 90
                    nop                                     ;[810e] 00
                    ld        h,$35                         ;[810f] 26 35
                    ld        a,a                           ;[8111] 7f
                    add       b                             ;[8112] 80
                    ld        a,(de)                        ;[8113] 1a
                    ld        h,l                           ;[8114] 65
                    xor       d                             ;[8115] aa
                    ld        b,d                           ;[8116] 42
                    ex        af,af'                        ;[8117] 08
                    xor       b                             ;[8118] a8
                    ld        b,b                           ;[8119] 40
                    and       c                             ;[811a] a1
                    djnz      $815f                         ;[811b] 10 42
                    sub       (hl)                          ;[811d] 96
                    ld        h,d                           ;[811e] 62
                    ld        b,b                           ;[811f] 40
                    ld        b,b                           ;[8120] 40
                    ld        h,d                           ;[8121] 62
                    ld        b,h                           ;[8122] 44
                    sub       l                             ;[8123] 95
                    dec       c                             ;[8124] 0d
                    ld        h,d                           ;[8125] 62
                    ld        e,a                           ;[8126] 5f
                    ld        e,$a4                         ;[8127] 1e a4
                    cp        a                             ;[8129] bf
                    ld        e,l                           ;[812a] 5d
                    ld        h,(hl)                        ;[812b] 66
                    ld        bc,$1ff0                      ;[812c] 01 f0 1f
                    call      m,$9446                       ;[812f] fc 46 94
                    add       b                             ;[8132] 80
                    sbc       h                             ;[8133] 9c
                    rrca                                    ;[8134] 0f
                    call      m,$7c0f                       ;[8135] fc 0f 7c
                    pop       af                            ;[8138] f1
                    ld        b,b                           ;[8139] 40
                    dec       de                            ;[813a] 1b
                    scf                                     ;[813b] 37
                    ld        e,c                           ;[813c] 59
                    nop                                     ;[813d] 00
                    ld        h,e                           ;[813e] 63
                    dec       c                             ;[813f] 0d
                    ld        e,l                           ;[8140] 5d
                    ld        e,b                           ;[8141] 58
                    ld        b,b                           ;[8142] 40
                    sub       $01                           ;[8143] d6 01
                    ld        h,l                           ;[8145] 65
                    dec       b                             ;[8146] 05
                    ret       nc                            ;[8147] dd d0
                    adc       d                             ;[8149] 8a
                    rst       $38                           ;[814a] ff
                    ei                                      ;[814b] fb
                    xor       c                             ;[814c] a9
                    ei                                      ;[814d] fb
                    ret       p                             ;[814e] f0
                    ld        d,b                           ;[814f] 50
                    or        e                             ;[8150] b3
                    ld        l,e                           ;[8151] 6b
                    add       d                             ;[8152] 82
                    ld        hl,($da55)                    ;[8153] 2a 55 da
                    ld        b,b                           ;[8156] 40
                    nop                                     ;[8157] 00
                    add       d                             ;[8158] 82
                    ld        a,h                           ;[8159] 7c
                    add       hl,de                         ;[815a] 19
                    ld        d,d                           ;[815b] 52
                    inc       a                             ;[815c] 3c
                    ld        a,h                           ;[815d] 7c
                    ld        d,d                           ;[815e] 52
                    ld        b,d                           ;[815f] 42
                    ld        l,c                           ;[8160] 69
                    dec       bc                            ;[8161] 0b
                    ld        d,(hl)                        ;[8162] 56
                    ex        af,af'                        ;[8163] 08
                    ld        l,a                           ;[8164] 6f
                    ld        (bc),a                        ;[8165] 02
                    ld        bc,$127f                      ;[8166] 01 7f 12
                    add       c                             ;[8169] 81
                    nop                                     ;[816a] 00
                    sbc       b                             ;[816b] 98
                    ld        c,b                           ;[816c] 48
                    ret       m                             ;[816d] f8
                    ccf                                     ;[816e] 3f
                    ld        c,b                           ;[816f] 48
                    sbc       d                             ;[8170] 9a
                    dec       b                             ;[8171] 05
                    ret       nc                            ;[8172] d0
                    dec       c                             ;[8173] 0d
                    ld        b,(hl)                        ;[8174] 46
                    push      de                            ;[8175] d5
                    rra                                     ;[8176] 1f
                    push      af                            ;[8177] f5
                    ret       p                             ;[8178] f0
                    pop       bc                            ;[8179] c1
                    nop                                     ;[817a] 00
                    xor       d                             ;[817b] aa
                    cpl                                     ;[817c] 2f
                    xor       a                             ;[817d] af
                    ld        a,h                           ;[817e] 7c
                    ld        b,b                           ;[817f] 40
                    dec       de                            ;[8180] 1b
                    xor       a                             ;[8181] af
                    ld        l,d                           ;[8182] 6a
                    nop                                     ;[8183] 00
                    ld        a,a                           ;[8184] 7f
                    ld        e,b                           ;[8185] 58
                    sub       c                             ;[8186] 91
                    dec       b                             ;[8187] 05
                    ld        a,(bc)                        ;[8188] dd 0a
                    rst       $18                           ;[818a] df
                    cp        $82                           ;[818b] fe 82
                    cpi                                     ;[818d] ed a1
                    ld        h,d                           ;[818f] 62
                    and       c                             ;[8190] a1
                    add       b                             ;[8191] 80
                    add       b                             ;[8192] 80
                    djnz      $8119                         ;[8193] 10 84
                    add       b                             ;[8195] 80
                    xor       e                             ;[8196] ab
                    jr        nz,$811d                      ;[8197] 20 84
                    add       h                             ;[8199] 84
                    sub       h                             ;[819a] 94
                    inc       b                             ;[819b] 04
                    sub       h                             ;[819c] 94
                    ld        e,b                           ;[819d] 58
                    nop                                     ;[819e] 00
                    add       hl,hl                         ;[819f] 29
                    rst       $28                           ;[81a0] ef
                    ld        b,(hl)                        ;[81a1] 46
                    ld        h,l                           ;[81a2] 65
                    ld        e,(hl)                        ;[81a3] 5e
                    ld        b,h                           ;[81a4] 44
                    add       e                             ;[81a5] 83
                    add       hl,de                         ;[81a6] 19
                    cp        h                             ;[81a7] bc
                    add       h                             ;[81a8] 84
                    rst       $30                           ;[81a9] f7
                    rst       $18                           ;[81aa] df
                    add       b                             ;[81ab] 80
                    ld        e,e                           ;[81ac] 5b
                    ld        a,a                           ;[81ad] 7f
                    ld        l,d                           ;[81ae] 6a
                    nop                                     ;[81af] 00
                    ret       nz                            ;[81b0] c0
                    ld        d,d                           ;[81b1] 52
                    ld        h,b                           ;[81b2] 60
                    push      de                            ;[81b3] d5
                    ld        d,l                           ;[81b4] 55
                    ld        d,l                           ;[81b5] 55
                    ld        l,d                           ;[81b6] 6a
                    ld        d,a                           ;[81b7] 57
                    push      hl                            ;[81b8] e5
                    rlca                                    ;[81b9] 07
                    ret       nz                            ;[81ba] c0
                    rst       $10                           ;[81bb] d7
                    nop                                     ;[81bc] 00
                    jr        z,$81cc                       ;[81bd] 28 0d
                    jr        $81a0                         ;[81bf] 18 df
                    rst       $18                           ;[81c1] df
                    nop                                     ;[81c2] fd 00
                    dec       e                             ;[81c4] 1d
                    nop                                     ;[81c5] 00
                    inc       bc                            ;[81c6] 03
                    ld        h,d                           ;[81c7] 62
                    ld        bc,$bf01                      ;[81c8] 01 01 bf
                    and       c                             ;[81cb] a1
                    ret       m                             ;[81cc] f8
                    xor       d                             ;[81cd] aa
                    ld        d,d                           ;[81ce] 52
                    add       h                             ;[81cf] 84
                    jr        z,$821a                       ;[81d0] 28 48
                    ld        h,$b4                         ;[81d2] 26 b4
                    adc       h                             ;[81d4] 8c
                    add       h                             ;[81d5] 84
                    adc       c                             ;[81d6] 89
                    adc       h                             ;[81d7] 8c
                    adc       b                             ;[81d8] 88
                    ld        h,b                           ;[81d9] 60
                    sub       h                             ;[81da] 94
                    dec       de                            ;[81db] 1b
                    rst       $30                           ;[81dc] f7
                    add       a                             ;[81dd] 87
                    rst       $38                           ;[81de] ff
                    ld        ($46be),hl                    ;[81df] 22 be 46
                    and       l                             ;[81e2] a5
                    rst       $38                           ;[81e3] ff
                    ex        af,af'                        ;[81e4] 08
                    jr        nz,$822c                      ;[81e5] 20 45
                    ret       p                             ;[81e7] f0
                    rrca                                    ;[81e8] 0f
                    ld        a,b                           ;[81e9] 78
                    ld        (hl),h                        ;[81ea] 74
                    ld        l,b                           ;[81eb] 68
                    ld        b,b                           ;[81ec] 40
                    sub       c                             ;[81ed] 91
                    call      c,$aa80                       ;[81ee] dc 80 aa
                    ld        bc,$51ff                      ;[81f1] 01 ff 51
                    or        (hl)                          ;[81f4] b6
                    ld        l,a                           ;[81f5] 6f
                    nop                                     ;[81f6] 00
                    ld        (de),a                        ;[81f7] 12
                    sub       $c0                           ;[81f8] d6 c0
                    ld        b,b                           ;[81fa] 40
                    dec       e                             ;[81fb] 1d
                    or        l                             ;[81fc] b5
                    add       l                             ;[81fd] 85
                    push      de                            ;[81fe] dd d5
                    nop                                     ;[8200] 00
                    ld        ($7f03),hl                    ;[8201] 22 03 7f
                    and       l                             ;[8204] a5
                    call      m,$7886                       ;[8205] fc 86 78
                    call      m,$307c                       ;[8208] fc 7c 30
                    call      m,$9625                       ;[820b] fc 25 96
                    ld        a,b                           ;[820e] 78
                    ld        c,b                           ;[820f] 48
                    add       h                             ;[8210] 84
                    ld        a,b                           ;[8211] 78
                    call      m,$f084                       ;[8212] fc 84 f0
                    ex        af,af'                        ;[8215] 08
                    rla                                     ;[8216] 17
                    ret       po                            ;[8217] e0
                    ld        bc,$12ff                      ;[8218] 01 ff 12
                    add       c                             ;[821b] 81
                    add       b                             ;[821c] 80
                    sbc       b                             ;[821d] 98
                    sbc       $c0                           ;[821e] de c0
                    inc       bc                            ;[8220] 03
                    call      z,$4800                       ;[8221] cc 00 48
                    jr        nz,$822f                      ;[8224] 20 09
                    jp        po,$300f                      ;[8226] e2 0f 30
                    ld        (hl),c                        ;[8229] 71
                    ld        h,b                           ;[822a] 60
                    ld        b,b                           ;[822b] 40
                    ld        l,e                           ;[822c] 6b
                    inc       bc                            ;[822d] 03
                    ld        a,(de)                        ;[822e] 1a
                    nop                                     ;[822f] 00
                    sub       c                             ;[8230] 91
                    ret       nz                            ;[8231] c0
                    ld        hl,($945f)                    ;[8232] 2a 5f 94
                    adc       a                             ;[8235] 8f
                    ld        h,$01                         ;[8236] 26 01
                    ld        a,a                           ;[8238] 7f
                    sub       (hl)                          ;[8239] 96
                    and       b                             ;[823a] a0
                    rst       $30                           ;[823b] f7
                    ld        a,(de)                        ;[823c] 1a
                    dec       e                             ;[823d] 1d
                    ld        de,$0262                      ;[823e] 11 62 02
                    cp        a                             ;[8241] bf
                    rst       $00                           ;[8242] c7
                    ret       nz                            ;[8243] c0
                    ld        b,d                           ;[8244] 42
                    ld        (hl),a                        ;[8245] 77
                    rra                                     ;[8246] 1f
                    or        b                             ;[8247] b0
                    add       hl,sp                         ;[8248] 39
                    nop                                     ;[8249] 00
                    ld        b,d                           ;[824a] 42
                    ld        (hl),l                        ;[824b] 75
                    cp        a                             ;[824c] bf
                    jp        nz,$c487                      ;[824d] c2 87 c4
                    nop                                     ;[8250] 00
                    ret       nz                            ;[8251] c0
                    sbc       c                             ;[8252] 99
                    ccf                                     ;[8253] 3f
                    inc       de                            ;[8254] 13
                    jp        po,$0344                      ;[8255] e2 44 03
                    ld        a,d                           ;[8258] 7a
                    nop                                     ;[8259] 00
                    rrca                                    ;[825a] 0f
                    ld        d,l                           ;[825b] 55
                    xor       e                             ;[825c] ab
                    ld        l,a                           ;[825d] 6f
                    rst       $18                           ;[825e] df
                    rlca                                    ;[825f] 07
                    ld        b,b                           ;[8260] 40
                    rla                                     ;[8261] 17
                    ld        e,d                           ;[8262] 5a
                    ld        bc,$ab1a                      ;[8263] 01 1a ab
                    rst       $28                           ;[8266] ef
                    ret       po                            ;[8267] e0
                    ld        sp,$21c0                      ;[8268] 31 c0 21
                    sub       h                             ;[826b] 94
                    rla                                     ;[826c] 17
                    ld        a,a                           ;[826d] 7f
                    rst       $18                           ;[826e] df
                    cp        a                             ;[826f] bf
                    ret       po                            ;[8270] e0
                    cp        (hl)                          ;[8271] be
                    add       b                             ;[8272] 80
                    add       b                             ;[8273] 80
                    add       e                             ;[8274] 83
                    add       b                             ;[8275] 80
                    xor       h                             ;[8276] ac
                    in        a,($fc)                       ;[8277] db fc
                    ld        c,d                           ;[8279] 4a
                    add       c                             ;[827a] 81
                    ld        b,iyh                         ;[827b] fd 44
                    sub       $05                           ;[827d] d6 05
                    cp        h                             ;[827f] bc
                    scf                                     ;[8280] 37
                    ccf                                     ;[8281] 3f
                    ret                                     ;[8282] c9

                    ld        b,b                           ;[8283] 40
                    ret       pe                            ;[8284] e8
                    add       c                             ;[8285] 81
                    ret       nz                            ;[8286] c0
                    ld        bc,$8b8e                      ;[8287] 01 8e 8b
                    push      de                            ;[828a] d5
                    call      c,$1b5d                       ;[828b] dc 5d 1b
                    ld        sp,hl                         ;[828e] f9
                    dec       e                             ;[828f] 1d
                    adc       c                             ;[8290] 89
                    ret       nz                            ;[8291] c0
                    adc       b                             ;[8292] 88
                    ccf                                     ;[8293] 3f
                    add       c                             ;[8294] 81
                    ld        a,a                           ;[8295] 7f
                    sub       a                             ;[8296] 97
                    di                                      ;[8297] f3
                    ret       m                             ;[8298] f8
                    jp        po,$c0c7                      ;[8299] e2 c7 c0
                    ld        (hl),l                        ;[829c] 75
                    ld        bc,$5cc1                      ;[829d] 01 c1 5c
                    or        c                             ;[82a0] b1
                    jp        m,$4088                       ;[82a1] fa 88 40
                    add       d                             ;[82a4] 82
                    ld        d,a                           ;[82a5] 57
                    rst       $38                           ;[82a6] ff
                    sub       b                             ;[82a7] 90
                    rra                                     ;[82a8] 1f
                    ld        h,d                           ;[82a9] 62
                    rrca                                    ;[82aa] 0f
                    ei                                      ;[82ab] fb
                    jp        po,$fe81                      ;[82ac] e2 81 fe
                    add       l                             ;[82af] 85
                    ld        (de),a                        ;[82b0] 12
                    xor       a                             ;[82b1] af
                    pop       bc                            ;[82b2] c1
                    call      nc,$ff8b                      ;[82b3] d4 8b ff
                    cp        $80                           ;[82b6] fe 80
                    ld        d,$45                         ;[82b8] 16 45
                    call      po,$43f5                      ;[82ba] e4 f5 43
                    and       (hl)                          ;[82bd] a6
                    in        a,($ff)                       ;[82be] db ff
                    cp        $a3                           ;[82c0] fe a3
                    rst       $28                           ;[82c2] ef
                    rst       $38                           ;[82c3] ff
                    rlca                                    ;[82c4] 07
                    cp        $1c                           ;[82c5] fe 1c
                    ret       p                             ;[82c7] f0
                    cp        $c2                           ;[82c8] fe c2
                    adc       b                             ;[82ca] 88
                    dec       l                             ;[82cb] 2d
                    rst       $10                           ;[82cc] d7
                    cp        b                             ;[82cd] b8
                    add       a                             ;[82ce] 87
                    cp        $c1                           ;[82cf] fe c1
                    pop       hl                            ;[82d1] e1
                    cp        l                             ;[82d2] bd
                    ex        de,hl                         ;[82d3] eb
                    ld        b,h                           ;[82d4] 44
                    ld        h,e                           ;[82d5] 63
                    rst       $18                           ;[82d6] df
                    ret       nz                            ;[82d7] c0
                    ld        h,c                           ;[82d8] 61
                    ld        bc,$02a9                      ;[82d9] 01 a9 02
                    cp        a                             ;[82dc] bf
                    dec       bc                            ;[82dd] 0b
                    sbc       a                             ;[82de] 9f
                    ccf                                     ;[82df] 3f
                    ret       nz                            ;[82e0] c0
                    ld        (hl),a                        ;[82e1] 77
                    call      nz,$82f6                      ;[82e2] c4 f6 82
                    ret       c                             ;[82e5] d8
                    ret       nc                            ;[82e6] d0
                    nop                                     ;[82e7] 00
                    xor       d                             ;[82e8] aa
                    rlca                                    ;[82e9] 07
                    rlca                                    ;[82ea] 07
                    ld        (de),a                        ;[82eb] 12
                    add       hl,sp                         ;[82ec] 39
                    add       b                             ;[82ed] 80
                    ld        bc,$dbc0                      ;[82ee] 01 c0 db
                    ld        a,a                           ;[82f1] 7f
                    ld        e,l                           ;[82f2] 5d
                    rst       $30                           ;[82f3] f7
                    ld        b,b                           ;[82f4] 40
                    nop                                     ;[82f5] 00
                    sbc       b                             ;[82f6] 98
                    dec       de                            ;[82f7] 1b
                    ret       nz                            ;[82f8] c0
                    cpl                                     ;[82f9] 2f
                    scf                                     ;[82fa] 37
                    add       b                             ;[82fb] 80
                    ld        (bc),a                        ;[82fc] 02
                    dec       l                             ;[82fd] 2d
                    cpl                                     ;[82fe] 2f
                    add       c                             ;[82ff] 81
                    adc       b                             ;[8300] 88
                    ld        bc,$fcfb                      ;[8301] 01 fb fc
                    ld        b,d                           ;[8304] 42
                    add       d                             ;[8305] 82
                    add       b                             ;[8306] 80
                    jr        nz,$8378                      ;[8307] 20 6f
                    rst       $08                           ;[8309] cf
                    cp        h                             ;[830a] bc
                    adc       a                             ;[830b] 8f
                    ret       nz                            ;[830c] c0
                    add       b                             ;[830d] 80
                    cp        a                             ;[830e] bf
                    pop       bc                            ;[830f] c1
                    call      c,$c01d                       ;[8310] dc 1d c0
                    inc       e                             ;[8313] 1c
                    inc       d                             ;[8314] 14
                    inc       e                             ;[8315] 1c
                    dec       e                             ;[8316] 1d
                    ld        b,c                           ;[8317] 41
                    or        a                             ;[8318] b7
                    ld        ($20c0),hl                    ;[8319] 22 c0 20
                    dec       bc                            ;[831c] 0b
                    di                                      ;[831d] f3
                    and       c                             ;[831e] a1
                    push      bc                            ;[831f] c5
                    ld        e,l                           ;[8320] 5d
                    xor       b                             ;[8321] a8
                    ld        b,c                           ;[8322] 41
                    or        $88                           ;[8323] f6 88
                    rst       $38                           ;[8325] ff
                    cp        e                             ;[8326] bb
                    ei                                      ;[8327] fb
                    rst       $18                           ;[8328] df
                    rst       $30                           ;[8329] f7
                    call      po,$71fd                      ;[832a] e4 fd 71
                    nop                                     ;[832d] 00
                    and       b                             ;[832e] a0
                    add       b                             ;[832f] 80
                    xor       d                             ;[8330] aa
                    in        a,($fb)                       ;[8331] db fb
                    ld        h,d                           ;[8333] 62
                    cp        e                             ;[8334] bb
                    sbc       e                             ;[8335] 9b
                    rrca                                    ;[8336] 0f
                    ld        c,c                           ;[8337] 49
                    ret       c                             ;[8338] d8
                    add       ixh                           ;[8339] dd 84
                    exx                                     ;[833b] d9
                    ld        a,$a3                         ;[833c] 3e a3
                    call      c,$8160                       ;[833e] dc 60 81
                    and       d                             ;[8341] a2
                    inc       bc                            ;[8342] 03
                    ld        a,(hl)                        ;[8343] 7e
                    dec       c                             ;[8344] 0d
                    ret       po                            ;[8345] e0
                    rst       $08                           ;[8346] cf
                    and       b                             ;[8347] a0
                    ld        b,c                           ;[8348] 41
                    sbc       c                             ;[8349] 99
                    dec       b                             ;[834a] 05
                    ret       m                             ;[834b] f8
                    dec       b                             ;[834c] 05
                    call      c,$1a00                       ;[834d] dc 00 1a
                    scf                                     ;[8350] 37
                    and       d                             ;[8351] a2
                    ld        e,a                           ;[8352] 5f
                    or        a                             ;[8353] b7
                    rst       $20                           ;[8354] e7
                    ld        b,b                           ;[8355] 40
                    ld        l,b                           ;[8356] 68
                    pop       bc                            ;[8357] c1
                    dec       a                             ;[8358] 3d
                    ei                                      ;[8359] fb
                    nop                                     ;[835a] ed 81
                    call      pe,$0089                      ;[835c] ec 89 00
                    add       e                             ;[835f] 83
                    ld        e,a                           ;[8360] 5f
                    add       e                             ;[8361] 83
                    ld        a,a                           ;[8362] 7f
                    ret       nz                            ;[8363] c0
                    ld        b,b                           ;[8364] 40
                    adc       l                             ;[8365] 8d
                    ccf                                     ;[8366] 3f
                    rst       $28                           ;[8367] ef
                    ret       pe                            ;[8368] e8
                    nop                                     ;[8369] 00
                    rlca                                    ;[836a] 07
                    ld        (bc),a                        ;[836b] 02
                    add       b                             ;[836c] 80
                    and       c                             ;[836d] dd a1
                    dec       b                             ;[836f] 05
                    or        e                             ;[8370] b3
                    push      hl                            ;[8371] e5
                    add       b                             ;[8372] 80
                    dec       c                             ;[8373] 0d
                    jp        nc,$fc73                      ;[8374] d2 73 fc
                    ld        (hl),$00                      ;[8377] 36 00
                    jr        z,$8370                       ;[8379] 28 f5
                    ld        l,e                           ;[837b] 6b
                    pop       bc                            ;[837c] c1
                    sub       c                             ;[837d] 91
                    ld        b,b                           ;[837e] 40
                    jp        c,$9701                       ;[837f] da 01 97
                    jr        $8309                         ;[8382] 18 85
                    ld        a,e                           ;[8384] 7b
                    ld        a,e                           ;[8385] 7b
                    ld        (hl),l                        ;[8386] 75
                    xor       b                             ;[8387] a8
                    ld        bc,$21fe                      ;[8388] 01 fe 21
                    rst       $38                           ;[838b] ff
                    rst       $38                           ;[838c] ff
                    ret       p                             ;[838d] f0
                    ld        h,b                           ;[838e] 60
                    ld        e,l                           ;[838f] 5d
                    xor       d                             ;[8390] aa
                    ld        bc,$0b0b                      ;[8391] 01 0b 0b
                    ld        e,$40                         ;[8394] 1e 40
                    ld        b,a                           ;[8396] 47
                    ld        l,d                           ;[8397] 6a
                    ld        bc,$942f                      ;[8398] 01 2f 94
                    ld        l,a                           ;[839b] 6f
                    ld        (hl),l                        ;[839c] 75
                    ld        e,l                           ;[839d] 5d
                    dec       b                             ;[839e] 05
                    ld        h,l                           ;[839f] 65
                    nop                                     ;[83a0] 00
                    and       b                             ;[83a1] a0
                    pop       bc                            ;[83a2] c1
                    inc       sp                            ;[83a3] 33
                    add       b                             ;[83a4] 80
                    ld        a,h                           ;[83a5] 7c
                    add       c                             ;[83a6] 81
                    ld        h,d                           ;[83a7] 62
                    nop                                     ;[83a8] 00
                    sub       b                             ;[83a9] 90
                    ld        b,a                           ;[83aa] 47
                    ld        h,e                           ;[83ab] 63
                    inc       bc                            ;[83ac] 03
                    rst       $28                           ;[83ad] ef
                    ld        a,d                           ;[83ae] 7a
                    nop                                     ;[83af] 00
                    exx                                     ;[83b0] d9
                    ld        b,d                           ;[83b1] 42
                    sub       b                             ;[83b2] 90
                    and       d                             ;[83b3] dd a2
                    ex        de,hl                         ;[83b5] eb
                    adc       d                             ;[83b6] 8a
                    ld        a,l                           ;[83b7] 7d
                    dec       c                             ;[83b8] dd 0d
                    sbc       a                             ;[83ba] 9f
                    ld        b,(hl)                        ;[83bb] 46
                    xor       $7d                           ;[83bc] ee 7d
                    scf                                     ;[83be] 37
                    adc       d                             ;[83bf] 8a
                    nop                                     ;[83c0] 00
                    xor       a                             ;[83c1] af
                    sbc       d                             ;[83c2] 9a
                    rst       $30                           ;[83c3] f7
                    rst       $38                           ;[83c4] ff
                    ld        a,a                           ;[83c5] 7f
                    cp        b                             ;[83c6] b8
                    ei                                      ;[83c7] fb
                    ld        b,d                           ;[83c8] 42
                    ld        e,l                           ;[83c9] 5d
                    ld        (hl),e                        ;[83ca] 73
                    nop                                     ;[83cb] 00
                    ld        b,h                           ;[83cc] 44
                    or        $c0                           ;[83cd] f6 c0
                    nop                                     ;[83cf] 00
                    ld        (de),a                        ;[83d0] 12
                    add       (hl)                          ;[83d1] 86
                    xor       (ix+$0f)                      ;[83d2] dd ae 0f
                    rrca                                    ;[83d5] 0f
                    pop       bc                            ;[83d6] c1
                    sub       (hl)                          ;[83d7] 96
                    rst       $30                           ;[83d8] f7
                    add       b                             ;[83d9] 80
                    add       c                             ;[83da] 81
                    or        l                             ;[83db] b5
                    pop       bc                            ;[83dc] c1
                    rst       $10                           ;[83dd] d7
                    nop                                     ;[83de] 00
                    ld        (hl),b                        ;[83df] 70
                    add       b                             ;[83e0] 80
                    inc       hl                            ;[83e1] 23
                    ld        e,a                           ;[83e2] 5f
                    inc       bc                            ;[83e3] 03
                    ld        a,d                           ;[83e4] 7a
                    nop                                     ;[83e5] 00
                    cp        $10                           ;[83e6] fe 10
                    and       b                             ;[83e8] a0
                    add       b                             ;[83e9] 80
                    xor       l                             ;[83ea] ad
                    ccf                                     ;[83eb] 3f
                    ld        e,(hl)                        ;[83ec] 5e
                    ld        b,b                           ;[83ed] 40
                    add       b                             ;[83ee] 80
                    inc       sp                            ;[83ef] 33
                    sub       $01                           ;[83f0] d6 01
                    daa                                     ;[83f2] 27
                    call      c,$8753                       ;[83f3] dd dc 53 87
                    ld        (hl),$c0                      ;[83f7] 36 c0
                    jr        nz,$83fc                      ;[83f9] 20 01
                    adc       a                             ;[83fb] 8f
                    and       c                             ;[83fc] a1
                    jp        c,$a15d                       ;[83fd] da 5d a1
                    ld        bc,$a1ed                      ;[8400] 01 ed a1
                    cp        l                             ;[8403] bd
                    ld        a,(bc)                        ;[8404] 0a
                    ret       nc                            ;[8405] d0
                    ld        a,(bc)                        ;[8406] 0a
                    sbc       a                             ;[8407] 9f
                    jr        $838d                         ;[8408] 18 83
                    rst       $30                           ;[840a] f7
                    ld        l,e                           ;[840b] 6b
                    rst       $28                           ;[840c] ef
                    ld        d,(hl)                        ;[840d] 56
                    ret       nz                            ;[840e] c0
                    jp        p,$c2e3                       ;[840f] f2 e3 c2
                    ld        e,c                           ;[8412] 59
                    nop                                     ;[8413] 00
                    xor       e                             ;[8414] ab
                    ld        b,$0a                         ;[8415] 06 0a
                    ld        e,d                           ;[8417] 5a
                    ld        b,c                           ;[8418] 41
                    cp        $44                           ;[8419] fe 44
                    sbc       l                             ;[841b] 9d
                    rra                                     ;[841c] 1f
                    ret       p                             ;[841d] f0
                    dec       de                            ;[841e] 1b
                    xor       b                             ;[841f] a8
                    nop                                     ;[8420] 00
                    ret       p                             ;[8421] f0
                    ld        c,d                           ;[8422] 4a
                    ld        d,d                           ;[8423] dd 52
                    xor       l                             ;[8425] ad
                    cpl                                     ;[8426] 2f
                    cp        a                             ;[8427] bf
                    adc       c                             ;[8428] 89
                    ld        b,c                           ;[8429] 41
                    rst       $30                           ;[842a] f7
                    ret       m                             ;[842b] f8
                    rra                                     ;[842c] 1f
                    jp        $c0f5                         ;[842d] c3 f5 c0
                    sub       d                             ;[8430] 92
                    nop                                     ;[8431] 00
                    inc       (hl)                          ;[8432] 34
                    ld        sp,hl                         ;[8433] f9
                    cp        $c1                           ;[8434] fe c1
                    add       b                             ;[8436] 80
                    ret       c                             ;[8437] d8
                    nop                                     ;[8438] 00
                    and       h                             ;[8439] a4
                    inc       de                            ;[843a] 13
                    dec       c                             ;[843b] 0d
                    ld        l,d                           ;[843c] 6a
                    ld        bc,$8adf                      ;[843d] 01 df 8a
                    di                                      ;[8440] f3
                    rst       $18                           ;[8441] df
                    ld        b,e                           ;[8442] 43
                    pop       de                            ;[8443] d1
                    ld        b,l                           ;[8444] 45
                    sub       d                             ;[8445] 92
                    ld        b,b                           ;[8446] 40
                    and       d                             ;[8447] a2
                    ex        de,hl                         ;[8448] eb
                    cp        $fd                           ;[8449] fe fd
                    ld        (hl),c                        ;[844b] 71
                    ret       nz                            ;[844c] c0
                    nop                                     ;[844d] 00
                    xor       e                             ;[844e] ab
                    xor       a                             ;[844f] af
                    rst       $28                           ;[8450] ef
                    ld        a,d                           ;[8451] 7a
                    nop                                     ;[8452] 00
                    sbc       e                             ;[8453] 9b
                    dec       d                             ;[8454] 15
                    push      bc                            ;[8455] c5
                    and       c                             ;[8456] a1
                    ld        b,c                           ;[8457] 41
                    cp        $63                           ;[8458] fe 63
                    inc       b                             ;[845a] 04
                    sbc       l                             ;[845b] 9d
                    ld        e,b                           ;[845c] 58
                    nop                                     ;[845d] 00
                    add       hl,bc                         ;[845e] 09
                    sbc       l                             ;[845f] 9d
                    rla                                     ;[8460] 17
                    ret       p                             ;[8461] f0
                    rla                                     ;[8462] 17
                    ld        (hl),b                        ;[8463] 70
                    ld        b,b                           ;[8464] 40
                    ld        (hl),l                        ;[8465] 75
                    or        (hl)                          ;[8466] b6
                    ld        bc,$01df                      ;[8467] 01 df 01
                    sub       c                             ;[846a] 91
                    di                                      ;[846b] f3
                    ld        ($855f),hl                    ;[846c] 22 5f 85
                    rra                                     ;[846f] 1f
                    ret       nz                            ;[8470] c0
                    dec       c                             ;[8471] 0d
                    jr        nc,$83f4                      ;[8472] 30 80
                    rst       $00                           ;[8474] c7
                    nop                                     ;[8475] 00
                    adc       c                             ;[8476] 89
                    cp        $09                           ;[8477] fe 09
                    inc       de                            ;[8479] 13
                    ld        a,l                           ;[847a] 7d
                    ld        a,($79b7)                     ;[847b] 3a b7 79
                    add       b                             ;[847e] 80
                    nop                                     ;[847f] 00
                    and       b                             ;[8480] a0
                    ex        af,af'                        ;[8481] 08
                    ld        e,d                           ;[8482] 5a
                    rst       $10                           ;[8483] d7
                    ld        c,d                           ;[8484] 4a
                    ei                                      ;[8485] fb
                    ld        a,h                           ;[8486] 7c
                    ex        (sp),hl                       ;[8487] e3
                    cp        c                             ;[8488] b9
                    sub       d                             ;[8489] 92
                    cp        $23                           ;[848a] fe 23
                    ret       nc                            ;[848c] d0
                    ld        b,b                           ;[848d] 40
                    ld        l,d                           ;[848e] 6a
                    nop                                     ;[848f] 00
                    ld        e,a                           ;[8490] 5f
                    ld        a,(bc)                        ;[8491] 0a
                    cp        c                             ;[8492] fd b9
                    rst       $30                           ;[8494] f7
                    ld        a,$1c                         ;[8495] 3e 1c
                    ld        e,(hl)                        ;[8497] 5e
                    ld        b,b                           ;[8498] 40
                    add       h                             ;[8499] 84
                    ld        b,$f7                         ;[849a] 06 f7
                    sbc       c                             ;[849c] 99
                    nop                                     ;[849d] 00
                    ld        (hl),c                        ;[849e] 71
                    ld        h,c                           ;[849f] 61
                    ld        b,b                           ;[84a0] 40
                    xor       h                             ;[84a1] ac
                    ret       po                            ;[84a2] e0
                    ld        a,(de)                        ;[84a3] 1a
                    ld        b,c                           ;[84a4] 41
                    rst       $30                           ;[84a5] f7
                    ld        b,(hl)                        ;[84a6] 46
                    sub       b                             ;[84a7] 90
                    xor       d                             ;[84a8] dd aa
                    cpl                                     ;[84aa] 2f
                    cp        a                             ;[84ab] bf
                    dec       c                             ;[84ac] 0d
                    ret       nz                            ;[84ad] c0
                    add       b                             ;[84ae] 80
                    adc       l                             ;[84af] 8d
                    ld        l,a                           ;[84b0] 6f
                    add       $c0                           ;[84b1] c6 c0
                    nop                                     ;[84b3] 00
                    rst       $38                           ;[84b4] ff
                    rst       $00                           ;[84b5] c7
                    ld        b,b                           ;[84b6] 40
                    rlca                                    ;[84b7] 07
                    ld        b,b                           ;[84b8] 40
                    adc       b                             ;[84b9] 88
                    add       iy,bc                         ;[84ba] fd 09
                    ld        d,e                           ;[84bc] 53
                    ld        h,d                           ;[84bd] 62
                    ld        bc,$82ff                      ;[84be] 01 ff 82
                    jr        $8471                         ;[84c1] 18 ae
                    rst       $20                           ;[84c3] e7
                    ret       nz                            ;[84c4] c0
                    rst       $38                           ;[84c5] ff
                    add       hl,sp                         ;[84c6] 39
                    sub       l                             ;[84c7] 95
                    ex        af,af'                        ;[84c8] 08
                    push      bc                            ;[84c9] c5
                    sbc       d                             ;[84ca] 9a
                    ld        b,b                           ;[84cb] 40
                    exx                                     ;[84cc] fd d9
                    nop                                     ;[84ce] 00
                    ld        c,d                           ;[84cf] 4a
                    call      c,$3fa2                       ;[84d0] dc a2 3f
                    xor       c                             ;[84d3] a9
                    ccf                                     ;[84d4] 3f
                    rst       $18                           ;[84d5] df
                    rla                                     ;[84d6] 17
                    and       (hl)                          ;[84d7] a6
                    pop       bc                            ;[84d8] c1
                    rst       $38                           ;[84d9] ff
                    and       d                             ;[84da] a2
                    cp        a                             ;[84db] bf
                    ei                                      ;[84dc] fb
                    rst       $28                           ;[84dd] ef
                    dec       d                             ;[84de] 15
                    adc       a                             ;[84df] 8f
                    jp        m,$857f                       ;[84e0] fa 7f 85
                    adc       $bd                           ;[84e3] ce bd
                    dec       a                             ;[84e5] 3d
                    ld        b,(hl)                        ;[84e6] 46
                    pop       bc                            ;[84e7] c1
                    ld        sp,$8e72                      ;[84e8] 31 72 8e
                    ld        (bc),a                        ;[84eb] 02
                    xor       d                             ;[84ec] aa
                    rst       $38                           ;[84ed] ff
                    jp        pe,$c1a8                      ;[84ee] ea a8 c1
                    ex        de,hl                         ;[84f1] eb
                    set       6,a                           ;[84f2] cb f7
                    jp        nz,$c0fd                      ;[84f4] c2 fd c0
                    or        e                             ;[84f7] b3
                    ld        b,e                           ;[84f8] 43
                    or        d                             ;[84f9] b2
                    dec       bc                            ;[84fa] 0b
                    ld        hl,($c3ea)                    ;[84fb] 2a ea c3
                    rst       $38                           ;[84fe] ff
                    add       b                             ;[84ff] 80
                    jp        m,$47d7                       ;[8500] fa d7 47
                    ret       nz                            ;[8503] c0
                    adc       e                             ;[8504] 8b
                    cp        $03                           ;[8505] fe 03
                    halt                                    ;[8507] 76
                    add       c                             ;[8508] 81
                    ld        a,a                           ;[8509] 7f
                    ld        ($e0c3),hl                    ;[850a] 22 c3 e0
                    ld        b,$03                         ;[850d] 06 03
                    call      m,$df1f                       ;[850f] fc 1f df
                    add       a                             ;[8512] 87
                    rst       $28                           ;[8513] ef
                    rst       $38                           ;[8514] ff
                    jp        po,$e8aa                      ;[8515] e2 aa e8
                    cp        a                             ;[8518] bf
                    nop                                     ;[8519] 00
                    or        a                             ;[851a] b7
                    ld        (bc),a                        ;[851b] fd 02
                    push      af                            ;[851d] f5
                    add       hl,sp                         ;[851e] 39
                    dec       l                             ;[851f] 2d
                    pop       af                            ;[8520] f1
                    di                                      ;[8521] f3
                    and       d                             ;[8522] a2
                    xor       (hl)                          ;[8523] ae
                    rst       $20                           ;[8524] e7
                    rst       $08                           ;[8525] cf
                    xor       $22                           ;[8526] ee 22
                    ld        e,e                           ;[8528] 5b
                    ld        (hl),d                        ;[8529] 72
                    jp        $cffb                         ;[852a] c3 fb cf
                    sbc       l                             ;[852d] 9d
                    ld        a,(hl)                        ;[852e] 7e
                    ld        a,(bc)                        ;[852f] 0a
                    xor       d                             ;[8530] aa
                    and       b                             ;[8531] a0
                    pop       bc                            ;[8532] c1
                    rst       $38                           ;[8533] ff
                    sbc       d                             ;[8534] 9a
                    rst       $38                           ;[8535] ff
                    cp        a                             ;[8536] bf
                    sbc       (hl)                          ;[8537] 9e
                    ld        bc,$07fc                      ;[8538] 01 fc 07
                    call      z,$c5e3                       ;[853b] cc e3 c5
                    adc       a                             ;[853e] 8f
                    add       a                             ;[853f] 87
                    cp        (hl)                          ;[8540] be
                    cp        d                             ;[8541] ba
                    add       l                             ;[8542] 85
                    ld        sp,hl                         ;[8543] f9
                    jp        m,$40fa                       ;[8544] fa fa 40
                    xor       b                             ;[8547] a8
                    and       $be                           ;[8548] e6 be
                    ld        a,d                           ;[854a] 7a
                    rst       $18                           ;[854b] df
                    rst       $18                           ;[854c] df
                    rlca                                    ;[854d] 07
                    ld        (hl),h                        ;[854e] 74
                    ld        a,d                           ;[854f] 7a
                    call      m,$fb0f                       ;[8550] fc 0f fb
                    ld        l,d                           ;[8553] 6a
                    ret       m                             ;[8554] f8
                    add       a                             ;[8555] 87
                    rst       $30                           ;[8556] f7
                    dec       a                             ;[8557] fd 3d
                    sbc       a                             ;[8559] 9f
                    ret       m                             ;[855a] f8
                    ccf                                     ;[855b] 3f
                    jp        pe,$a6c0                      ;[855c] ea c0 a6
                    add       b                             ;[855f] 80
                    ld        (hl),a                        ;[8560] 77
                    ei                                      ;[8561] fb
                    ccf                                     ;[8562] 3f
                    cp        $00                           ;[8563] fe 00
                    jr        z,$856a                       ;[8565] 28 03
                    ld        a,(bc)                        ;[8567] 0a
                    ld        hl,($9802)                    ;[8568] 2a 02 98
                    cp        a                             ;[856b] bf
                    add       b                             ;[856c] 80
                    rst       $18                           ;[856d] fd df
                    ld        a,(de)                        ;[856f] 1a
                    inc       bc                            ;[8570] 03
                    ld        l,b                           ;[8571] 68
                    ret       nz                            ;[8572] c0
                    ld        c,b                           ;[8573] 48
                    xor       $05                           ;[8574] ee 05
                    ld        d,l                           ;[8576] 55
                    rst       $38                           ;[8577] ff
                    ld        d,h                           ;[8578] 54
                    pop       bc                            ;[8579] c1
                    dec       l                             ;[857a] 2d
                    rst       $20                           ;[857b] e7
                    ccf                                     ;[857c] 3f
                    sbc       a                             ;[857d] 9f
                    ld        b,b                           ;[857e] 40
                    ld        l,d                           ;[857f] 6a
                    sbc       $3f                           ;[8580] de 3f
                    ret       nz                            ;[8582] c0
                    ld        a,(de)                        ;[8583] 1a
                    dec       d                             ;[8584] 15
                    ld        l,d                           ;[8585] 6a
                    ld        d,l                           ;[8586] 55
                    ld        (hl),$c0                      ;[8587] 36 c0
                    rst       $08                           ;[8589] cf
                    nop                                     ;[858a] 00
                    ld        a,(de)                        ;[858b] 1a
                    rst       $38                           ;[858c] ff
                    xor       b                             ;[858d] a8
                    inc       bc                            ;[858e] 03
                    jr        z,$8590                       ;[858f] 28 ff
                    add       e                             ;[8591] 83
                    pop       bc                            ;[8592] c1
                    rst       $38                           ;[8593] ff
                    sbc       e                             ;[8594] 9b
                    ld        a,a                           ;[8595] 7f
                    rst       $28                           ;[8596] ef
                    ld        sp,hl                         ;[8597] f9
                    ret       m                             ;[8598] f8
                    add       a                             ;[8599] 87
                    add       c                             ;[859a] 81
                    pop       bc                            ;[859b] c1
                    call      c,$fbc0                       ;[859c] dc c0 fb
                    nop                                     ;[859f] 00
                    adc       b                             ;[85a0] 88
                    ld        a,a                           ;[85a1] 7f
                    add       e                             ;[85a2] 83
                    xor       d                             ;[85a3] aa
                    cp        a                             ;[85a4] bf
                    jp        $8f08                         ;[85a5] c3 08 8f
                    ld        a,b                           ;[85a8] 78
                    rrca                                    ;[85a9] 0f
                    or        l                             ;[85aa] b5
                    and       a                             ;[85ab] a7
                    ld        a,h                           ;[85ac] 7c
                    ld        b,b                           ;[85ad] 40
                    inc       b                             ;[85ae] 04
                    ret       nz                            ;[85af] c0
                    call      c,$00ff                       ;[85b0] dc ff 00
                    adc       b                             ;[85b3] 88
                    rst       $38                           ;[85b4] ff
                    rrca                                    ;[85b5] 0f
                    ld        l,c                           ;[85b6] 69
                    di                                      ;[85b7] f3
                    dec       sp                            ;[85b8] 3b
                    ex        de,hl                         ;[85b9] fd fd eb
                    ld        b,b                           ;[85bc] 40
                    ld        d,b                           ;[85bd] 50
                    or        a                             ;[85be] b7
                    cp        $00                           ;[85bf] fe 00
                    adc       b                             ;[85c1] 88
                    rst       $28                           ;[85c2] ef
                    rrca                                    ;[85c3] 0f
                    and       d                             ;[85c4] a2
                    ei                                      ;[85c5] fb
                    xor       b                             ;[85c6] a8
                    call      m,$62f3                       ;[85c7] fc f3 62
                    ret       p                             ;[85ca] f0
                    rra                                     ;[85cb] 1f
                    dec       l                             ;[85cc] 2d
                    push      de                            ;[85cd] d5
                    rst       $38                           ;[85ce] ff
                    cp        (hl)                          ;[85cf] be
                    push      bc                            ;[85d0] c5
                    cp        (hl)                          ;[85d1] be
                    or        $a3                           ;[85d2] f6 a3
                    ld        bc,$8096                      ;[85d4] 01 96 80
                    ld        (bc),a                        ;[85d7] 02
                    inc       b                             ;[85d8] 04
                    add       e                             ;[85d9] 83
                    sub       d                             ;[85da] 92
                    rst       $20                           ;[85db] e7
                    ld        b,h                           ;[85dc] 44
                    inc       bc                            ;[85dd] 03
                    ld        (hl),a                        ;[85de] 77
                    ld        (hl),b                        ;[85df] 70
                    and       c                             ;[85e0] a1
                    djnz      $856b                         ;[85e1] 10 88
                    cp        $7f                           ;[85e3] fe 7f
                    ld        h,d                           ;[85e5] 62
                    cp        $3f                           ;[85e6] fe 3f
                    inc       b                             ;[85e8] 04
                    adc       c                             ;[85e9] 89
                    ld        hl,($a8a8)                    ;[85ea] 2a a8 a8
                    di                                      ;[85ed] f3
                    sub       d                             ;[85ee] 92
                    cp        $ff                           ;[85ef] fe ff
                    sub       h                             ;[85f1] 94
                    rlca                                    ;[85f2] 07
                    or        e                             ;[85f3] b3
                    ld        a,(bc)                        ;[85f4] 0a
                    nop                                     ;[85f5] 00
                    jp        c,$2000                       ;[85f6] da 00 20
                    adc       a                             ;[85f9] 8f
                    adc       a                             ;[85fa] 8f
                    ld        l,c                           ;[85fb] 69
                    rst       $08                           ;[85fc] cf
                    rrca                                    ;[85fd] 0f
                    pop       bc                            ;[85fe] c1
                    ld        e,(hl)                        ;[85ff] 5e
                    nop                                     ;[8600] 00
                    adc       d                             ;[8601] 8a
                    ret       po                            ;[8602] e0
                    ld        a,a                           ;[8603] 7f
                    rra                                     ;[8604] 1f
                    add       d                             ;[8605] 82
                    in        a,($8f)                       ;[8606] db 8f
                    jp        $c57f                         ;[8608] c3 7f c5
                    ld        l,e                           ;[860b] 6b
                    ld        a,b                           ;[860c] fd 78
                    nop                                     ;[860e] 00
                    and       d                             ;[860f] a2
                    call      m,$aaa8                       ;[8610] fc a8 aa
                    xor       b                             ;[8613] a8
                    adc       b                             ;[8614] 88
                    ret       po                            ;[8615] e0
                    ret       po                            ;[8616] e0
                    add       hl,hl                         ;[8617] 29
                    cp        $89                           ;[8618] fe 89
                    ld        a,h                           ;[861a] 7c
                    inc       bc                            ;[861b] 03
                    adc       b                             ;[861c] 88
                    rst       $28                           ;[861d] ef
                    rst       $28                           ;[861e] ef
                    add       d                             ;[861f] 82
                    ld        l,a                           ;[8620] 6f
                    rst       $28                           ;[8621] ef
                    ld        (hl),b                        ;[8622] 70
                    rra                                     ;[8623] 1f
                    ld        a,e                           ;[8624] 7b
                    ld        l,c                           ;[8625] 69
                    ld        hl,($838f)                    ;[8626] 2a 8f 83
                    ex        af,af'                        ;[8629] 08
                    ret       po                            ;[862a] e0
                    rst       $38                           ;[862b] ff
                    ld        h,a                           ;[862c] 67
                    nop                                     ;[862d] 00
                    out       ($c5),a                       ;[862e] d3 c5
                    dec       e                             ;[8630] dd 1d
                    ld        l,c                           ;[8632] 69
                    xor       d                             ;[8633] aa
                    ld        bc,$c7fd                      ;[8634] 01 fd c7
                    and       e                             ;[8637] a3
                    rst       $30                           ;[8638] f7
                    cp        (hl)                          ;[8639] be
                    xor       a                             ;[863a] af
                    call      m,$bb40                       ;[863b] fc 40 bb
                    xor       b                             ;[863e] a8
                    cp        $7a                           ;[863f] fe 7a
                    nop                                     ;[8641] 00
                    rst       $30                           ;[8642] f7
                    ld        h,d                           ;[8643] 62
                    ret       p                             ;[8644] f0
                    ret       m                             ;[8645] f8
                    ld        ($cf1f),hl                    ;[8646] 22 1f cf
                    cp        h                             ;[8649] bc
                    adc       a                             ;[864a] 8f
                    ld        b,c                           ;[864b] 41
                    in        a,($89)                       ;[864c] db 89
                    ld        bc,$ff1e                      ;[864e] 01 1e ff
                    add       e                             ;[8651] 83
                    ret       nz                            ;[8652] c0
                    cp        $df                           ;[8653] fe df
                    nop                                     ;[8655] 00
                    ld        e,b                           ;[8656] 58
                    call      z,$b724                       ;[8657] cc 24 b7
                    dec       bc                            ;[865a] 0b
                    ld        b,d                           ;[865b] 42
                    ld        d,d                           ;[865c] 52
                    sub       h                             ;[865d] 94
                    ld        b,h                           ;[865e] 44
                    inc       bc                            ;[865f] 03
                    inc       b                             ;[8660] 04
                    sbc       $00                           ;[8661] de 00
                    add       a                             ;[8663] 87
                    call      m,$f66f                       ;[8664] fc 6f f6
                    jp        nz,$c335                      ;[8667] c2 35 c3
                    ld        a,$68                         ;[866a] 3e 68
                    nop                                     ;[866c] 00
                    add       b                             ;[866d] 80
                    push      af                            ;[866e] f5
                    rst       $38                           ;[866f] ff
                    push      af                            ;[8670] f5
                    dec       a                             ;[8671] 3d
                    and       c                             ;[8672] a1
                    ld        bc,$2980                      ;[8673] 01 80 29
                    dec       d                             ;[8676] 15
                    ld        h,$d7                         ;[8677] 26 d7
                    ret       po                            ;[8679] e0
                    ld        e,a                           ;[867a] 5f
                    ld        b,$20                         ;[867b] 06 20
                    add       e                             ;[867d] 83
                    ret       p                             ;[867e] f0
                    jr        $865f                         ;[867f] 18 de
                    ret       p                             ;[8681] f0
                    xor       a                             ;[8682] af
                    nop                                     ;[8683] 00
                    add       b                             ;[8684] 80
                    ei                                      ;[8685] fb
                    xor       d                             ;[8686] aa
                    rst       $00                           ;[8687] c7
                    rst       $08                           ;[8688] cf
                    ld        hl,($98fe)                    ;[8689] 2a fe 98
                    rst       $30                           ;[868c] f7
                    ld        l,c                           ;[868d] 69
                    ld        d,b                           ;[868e] fd 50
                    ld        bc,$5455                      ;[8690] 01 55 54
                    sub       (hl)                          ;[8693] 96
                    ret       p                             ;[8694] f0
                    ret       p                             ;[8695] f0
                    rst       $30                           ;[8696] f7
                    adc       d                             ;[8697] 8a
                    ld        (hl),b                        ;[8698] 70
                    cp        a                             ;[8699] bf
                    jr        z,$869b                       ;[869a] 28 ff
                    add       d                             ;[869c] 82
                    adc       a                             ;[869d] 8f
                    ret       po                            ;[869e] e0
                    pop       af                            ;[869f] f1
                    rst       $18                           ;[86a0] df
                    ld        a,(hl)                        ;[86a1] 7e
                    ld        l,c                           ;[86a2] 69
                    add       d                             ;[86a3] 82
                    adc       a                             ;[86a4] 8f
                    inc       d                             ;[86a5] 14
                    ret       p                             ;[86a6] f0
                    rst       $38                           ;[86a7] ff
                    ld        a,d                           ;[86a8] 7a
                    nop                                     ;[86a9] 00
                    rst       $18                           ;[86aa] df
                    ld        l,b                           ;[86ab] 68
                    rst       $38                           ;[86ac] ff
                    sbc       c                             ;[86ad] 99
                    rra                                     ;[86ae] 1f
                    ei                                      ;[86af] fb
                    rst       $08                           ;[86b0] cf
                    rst       $10                           ;[86b1] d7
                    ld        b,l                           ;[86b2] 45
                    ld        e,d                           ;[86b3] 5a
                    nop                                     ;[86b4] 00
                    ld        hl,$fee3                      ;[86b5] 21 e3 fe
                    jp        $d9ff                         ;[86b8] fd c3 ff d9
                    nop                                     ;[86bc] 00
                    dec       h                             ;[86bd] 25
                    ret       po                            ;[86be] e0
                    nop                                     ;[86bf] 00
                    rlca                                    ;[86c0] 07
                    add       b                             ;[86c1] 80
                    inc       bc                            ;[86c2] 03
                    rlca                                    ;[86c3] 07
                    rst       $18                           ;[86c4] df
                    xor       l                             ;[86c5] ad
                    ret       nz                            ;[86c6] c0
                    xor       a                             ;[86c7] af
                    ld        d,h                           ;[86c8] 54
                    cp        $da                           ;[86c9] fe da
                    nop                                     ;[86cb] 00
                    add       c                             ;[86cc] 81
                    ld        a,e                           ;[86cd] 7b
                    and       d                             ;[86ce] a2
                    ld        a,(bc)                        ;[86cf] 0a
                    adc       d                             ;[86d0] 8a
                    sub       a                             ;[86d1] 97
                    ld        (hl),a                        ;[86d2] 77
                    ld        (bc),a                        ;[86d3] 02
                    sub       b                             ;[86d4] 90
                    adc       iyl                           ;[86d5] fd 8d
                    ccf                                     ;[86d7] 3f
                    ret       p                             ;[86d8] f0
                    ld        (hl),c                        ;[86d9] 71
                    ld        a,(hl)                        ;[86da] 7e
                    or        a                             ;[86db] b7
                    add       b                             ;[86dc] 80
                    nop                                     ;[86dd] 00
                    and       l                             ;[86de] a5
                    and       a                             ;[86df] fd a7
                    rra                                     ;[86e1] 1f
                    ld        d,a                           ;[86e2] 57
                    ld        b,b                           ;[86e3] 40
                    ld        e,l                           ;[86e4] 5d
                    cp        l                             ;[86e5] bd
                    nop                                     ;[86e6] 00
                    rst       $00                           ;[86e7] c7
                    ld        h,(hl)                        ;[86e8] 66
                    nop                                     ;[86e9] 00
                    adc       b                             ;[86ea] 88
                    ld        bc,$8084                      ;[86eb] 01 84 80
                    ret       po                            ;[86ee] e0
                    and       h                             ;[86ef] a4
                    ret       po                            ;[86f0] e0
                    adc       l                             ;[86f1] 8d
                    ret       m                             ;[86f2] f8
                    or        a                             ;[86f3] b7
                    jp        c,$0f00                       ;[86f4] da 00 0f
                    ld        c,b                           ;[86f7] 48
                    add       (hl)                          ;[86f8] 86
                    rst       $18                           ;[86f9] df
                    cp        $ed                           ;[86fa] fe ed
                    rst       $30                           ;[86fc] f7
                    push      af                            ;[86fd] f5
                    and       b                             ;[86fe] a0
                    ld        a,l                           ;[86ff] 7d
                    nop                                     ;[8700] 00
                    ld        c,l                           ;[8701] 4d
                    exx                                     ;[8702] d9
                    ret       c                             ;[8703] d8
                    nop                                     ;[8704] 00
                    adc       d                             ;[8705] 8a
                    nop                                     ;[8706] 00
                    rlca                                    ;[8707] 07
                    rra                                     ;[8708] 1f
                    ld        l,d                           ;[8709] 6a
                    rst       $18                           ;[870a] df
                    rst       $30                           ;[870b] f7
                    rst       $20                           ;[870c] e7
                    or        a                             ;[870d] b7
                    add       hl,sp                         ;[870e] 39
                    ld        l,(hl)                        ;[870f] 6e
                    ld        h,l                           ;[8710] 65
                    ld        (bc),a                        ;[8711] 02
                    add       c                             ;[8712] 81
                    cpl                                     ;[8713] 2f
                    nop                                     ;[8714] 00
                    jr        z,$8716                       ;[8715] 28 ff
                    ld        h,c                           ;[8717] 61
                    nop                                     ;[8718] 00
                    and       d                             ;[8719] a2
                    cp        $86                           ;[871a] fe 86
                    rst       $00                           ;[871c] c7
                    and       d                             ;[871d] a2
                    rrca                                    ;[871e] 0f
                    rst       $18                           ;[871f] df
                    pop       hl                            ;[8720] e1
                    jp        m,$a840                       ;[8721] fa 40 a8
                    trap                                    ;[8724] ed fe
                    sbc       h                             ;[8726] 9c
                    nop                                     ;[8727] 00
                    ld        (hl),e                        ;[8728] 73
                    add       $b6                           ;[8729] c6 b6
                    or        c                             ;[872b] b1
                    rrca                                    ;[872c] 0f
                    and       a                             ;[872d] a7
                    ret       p                             ;[872e] dd f0
                    cp        (hl)                          ;[8730] be
                    rst       $38                           ;[8731] ff
                    pop       bc                            ;[8732] c1
                    cp        $5a                           ;[8733] fe 5a
                    nop                                     ;[8735] 00
                    sub       b                             ;[8736] 90
                    ld        (hl),a                        ;[8737] 77
                    or        (hl)                          ;[8738] b6
                    call      po,$8401                      ;[8739] e4 01 84
                    dec       d                             ;[873c] 15
                    ld        ($ff07),hl                    ;[873d] 22 07 ff
                    ld        b,e                           ;[8740] 43
                    dec       hl                            ;[8741] 2b
                    add       d                             ;[8742] 82
                    ld        b,b                           ;[8743] 40
                    ld        a,d                           ;[8744] 7a
                    nop                                     ;[8745] 00
                    ei                                      ;[8746] fb
                    jr        z,$8738                       ;[8747] 28 ef
                    add       hl,hl                         ;[8749] 29
                    ccf                                     ;[874a] 3f
                    rra                                     ;[874b] 1f
                    jp        $fef7                         ;[874c] c3 f7 fe
                    nop                                     ;[874f] 00
                    dec       bc                            ;[8750] 0b
                    cp        $16                           ;[8751] fe 16
                    adc       $5d                           ;[8753] ce 5d
                    inc       bc                            ;[8755] 03
                    sub       l                             ;[8756] 95
                    ld        e,b                           ;[8757] 58
                    nop                                     ;[8758] 00
                    xor       a                             ;[8759] af
                    call      m,$60cb                       ;[875a] fc cb 60
                    nop                                     ;[875d] 00
                    and       (hl)                          ;[875e] a6
                    rst       $28                           ;[875f] ef
                    sub       a                             ;[8760] 97
                    rst       $28                           ;[8761] ef
                    pop       bc                            ;[8762] c1
                    sbc       e                             ;[8763] 9b
                    rst       $10                           ;[8764] d7
                    rst       $00                           ;[8765] c7
                    add       e                             ;[8766] 83
                    adc       c                             ;[8767] 89
                    pop       bc                            ;[8768] c1
                    call      m,$ddef                       ;[8769] fc ef dd
                    inc       bc                            ;[876c] 03
                    add       (hl)                          ;[876d] 86
                    nop                                     ;[876e] 00
                    ld        h,c                           ;[876f] 61
                    rst       $08                           ;[8770] cf
                    rst       $08                           ;[8771] cf
                    rst       $30                           ;[8772] f7
                    cp        l                             ;[8773] bd
                    ret       p                             ;[8774] f0
                    ld        a,a                           ;[8775] 7f
                    inc       hl                            ;[8776] 23
                    jp        $fc54                         ;[8777] c3 54 fc
                    exx                                     ;[877a] d9
                    rst       $38                           ;[877b] ff
                    nop                                     ;[877c] 00
                    and       d                             ;[877d] a2
                    rlca                                    ;[877e] 07
                    xor       d                             ;[877f] aa
                    rrca                                    ;[8780] fd 0f
                    xor       c                             ;[8782] a9
                    di                                      ;[8783] f3
                    rst       $08                           ;[8784] cf
                    dec       (hl)                          ;[8785] 35
                    jp        m,$4057                       ;[8786] fa 57 40
                    ld        d,b                           ;[8789] 50
                    cp        (iy-$18)                      ;[878a] fd be e8
                    nop                                     ;[878d] 00
                    rra                                     ;[878e] 1f
                    cp        c                             ;[878f] b9
                    add       b                             ;[8790] 80
                    ld        c,$b1                         ;[8791] 0e b1
                    rrca                                    ;[8793] 0f
                    ex        (sp),hl                       ;[8794] e3
                    ld        e,e                           ;[8795] 5b
                    call      pe,$c0ff                      ;[8796] ec ff c0
                    add       c                             ;[8799] 81
                    cp        $d8                           ;[879a] fe d8
                    ld        bc,$f781                      ;[879c] 01 81 f7
                    ld        l,a                           ;[879f] 6f
                    adc       d                             ;[87a0] 8a
                    inc       b                             ;[87a1] 04
                    add       d                             ;[87a2] 82
                    add       l                             ;[87a3] 85
                    sub       h                             ;[87a4] 94
                    adc       c                             ;[87a5] 89
                    ld        sp,hl                         ;[87a6] f9
                    rst       $38                           ;[87a7] ff
                    and       c                             ;[87a8] a1
                    rst       $18                           ;[87a9] df
                    adc       c                             ;[87aa] 89
                    rra                                     ;[87ab] 1f
                    rst       $38                           ;[87ac] ff
                    inc       e                             ;[87ad] 1c
                    ld        e,d                           ;[87ae] 5a
                    ld        b,b                           ;[87af] 40
                    adc       c                             ;[87b0] 89
                    and       b                             ;[87b1] a0
                    add       h                             ;[87b2] 84
                    di                                      ;[87b3] f3
                    rst       $38                           ;[87b4] ff
                    ei                                      ;[87b5] fb
                    and       c                             ;[87b6] a1
                    cp        $68                           ;[87b7] fe 68
                    xor       d                             ;[87b9] aa
                    ld        (hl),$01                      ;[87ba] 36 01
                    add       h                             ;[87bc] 84
                    rst       $08                           ;[87bd] cf
                    ld        l,h                           ;[87be] 6c
                    inc       bc                            ;[87bf] 03
                    ld        (hl),l                        ;[87c0] 75
                    jp        $83ba                         ;[87c1] c3 ba 83
                    and       d                             ;[87c4] a2
                    ret       nz                            ;[87c5] c0
                    ld        (hl),$fe                      ;[87c6] 36 fe
                    or        a                             ;[87c8] b7
                    nop                                     ;[87c9] 00
                    ld        ($7fff),hl                    ;[87ca] 22 ff 7f
                    and       h                             ;[87cd] a4
                    rra                                     ;[87ce] 1f
                    dec       hl                            ;[87cf] 2b
                    add       e                             ;[87d0] 83
                    cp        a                             ;[87d1] bf
                    cp        a                             ;[87d2] bf
                    add       b                             ;[87d3] 80
                    cp        l                             ;[87d4] bd
                    ld        b,c                           ;[87d5] 41
                    ld        e,a                           ;[87d6] 5f
                    ld        e,c                           ;[87d7] 59
                    add       e                             ;[87d8] 83
                    ld        a,d                           ;[87d9] 7a
                    nop                                     ;[87da] 00
                    cp        a                             ;[87db] bf
                    ld        (hl),h                        ;[87dc] 74
                    ld        a,a                           ;[87dd] 7f
                    ld        b,b                           ;[87de] 40
                    pop       bc                            ;[87df] c1
                    ld        h,d                           ;[87e0] 62
                    nop                                     ;[87e1] 00
                    ld        ($f7ef),hl                    ;[87e2] 22 ef f7
                    ld        (bc),a                        ;[87e5] 02
                    ld        a,(bc)                        ;[87e6] 0a
                    xor       d                             ;[87e7] aa
                    xor       b                             ;[87e8] a8
                    ld        a,(bc)                        ;[87e9] 0a
                    xor       b                             ;[87ea] a8
                    cp        $fe                           ;[87eb] fe fe
                    cp        $46                           ;[87ed] fe 46
                    xor       b                             ;[87ef] a8
                    rst       $38                           ;[87f0] ff
                    rst       $28                           ;[87f1] ef
                    adc       c                             ;[87f2] 89
                    rst       $38                           ;[87f3] ff
                    rst       $30                           ;[87f4] f7
                    or        l                             ;[87f5] b5
                    ld        a,a                           ;[87f6] 7f
                    cp        $40                           ;[87f7] fe 40
                    pop       bc                            ;[87f9] c1
                    cp        (hl)                          ;[87fa] be
                    ld        (hl),a                        ;[87fb] 77
                    ld        a,a                           ;[87fc] 7f
                    cp        c                             ;[87fd] b9
                    ld        b,d                           ;[87fe] 42
                    ld        a,(bc)                        ;[87ff] 0a
                    or        (hl)                          ;[8800] b6
                    ret       nz                            ;[8801] c0
                    ld        bc,$0ec7                      ;[8802] 01 c7 0e
                    cp        (hl)                          ;[8805] be
                    ccf                                     ;[8806] 3f
                    dec       a                             ;[8807] 3d
                    pop       bc                            ;[8808] c1
                    pop       af                            ;[8809] f1
                    cp        $88                           ;[880a] fe 88
                    ld        bc,$1ff8                      ;[880c] 01 f8 1f
                    add       hl,de                         ;[880f] 19
                    xor       d                             ;[8810] aa
                    inc       bc                            ;[8811] 03
                    inc       bc                            ;[8812] 03
                    adc       h                             ;[8813] 8c
                    ld        (hl),a                        ;[8814] 77
                    or        b                             ;[8815] b0
                    rla                                     ;[8816] 17
                    ld        (hl),d                        ;[8817] 72
                    ld        b,b                           ;[8818] 40
                    sub       (hl)                          ;[8819] 96
                    or        $a9                           ;[881a] f6 a9
                    rst       $38                           ;[881c] ff
                    nop                                     ;[881d] 00
                    ld        c,e                           ;[881e] 4b
                    ld        bc,$423d                      ;[881f] 01 3d 42
                    xor       d                             ;[8822] aa
                    ld        bc,$f7eb                      ;[8823] 01 eb f7
                    ld        b,a                           ;[8826] 47
                    ld        e,l                           ;[8827] 5d
                    nop                                     ;[8828] 00
                    ld        l,d                           ;[8829] 6a
                    rst       $18                           ;[882a] df
                    sub       d                             ;[882b] 92
                    cp        a                             ;[882c] bf
                    xor       b                             ;[882d] a8
                    inc       bc                            ;[882e] 03
                    call      m,$2a09                       ;[882f] fc 09 2a
                    inc       bc                            ;[8832] 03
                    rst       $00                           ;[8833] c7
                    add       a                             ;[8834] 87
                    rst       $20                           ;[8835] e7
                    rst       $38                           ;[8836] ff
                    add       l                             ;[8837] 85
                    ld        bc,$ff6b                      ;[8838] 01 6b ff
                    ld        b,$40                         ;[883b] 06 40
                    dec       c                             ;[883d] 0d
                    add       e                             ;[883e] 83
                    nop                                     ;[883f] 00
                    rlca                                    ;[8840] 07
                    ld        b,b                           ;[8841] 40
                    in        a,($00)                       ;[8842] db 00
                    ld        d,e                           ;[8844] 53
                    ccf                                     ;[8845] 3f
                    call      m,$7f40                       ;[8846] fc 40 7f
                    ld        e,$40                         ;[8849] 1e 40
                    adc       d                             ;[884b] 8a
                    ld        a,a                           ;[884c] 7f
                    ret       po                            ;[884d] e0
                    ld        c,d                           ;[884e] 4a
                    rst       $18                           ;[884f] df
                    ld        a,(bc)                        ;[8850] 0a
                    rst       $18                           ;[8851] df
                    ld        l,l                           ;[8852] 6d
                    ret       m                             ;[8853] f8
                    ld        l,b                           ;[8854] 68
                    add       h                             ;[8855] 84
                    call      m,$5015                       ;[8856] fc 15 50
                    add       $18                           ;[8859] c6 18
                    ld        b,b                           ;[885b] 40
                    jr        z,$885c                       ;[885c] 28 fe
                    ld        a,(bc)                        ;[885e] 0a
                    rst       $30                           ;[885f] f7
                    ld        l,h                           ;[8860] 6c
                    ld        a,a                           ;[8861] 7f
                    ld        e,a                           ;[8862] 5f
                    ld        b,b                           ;[8863] 40
                    ret       nz                            ;[8864] dd c0
                    push      af                            ;[8866] f5
                    nop                                     ;[8867] 00
                    dec       e                             ;[8868] 1d
                    jp        m,$0e89                       ;[8869] fa 89 0e
                    inc       c                             ;[886c] 0c
                    dec       a                             ;[886d] 3d
                    add       a                             ;[886e] 87
                    xor       a                             ;[886f] af
                    sbc       c                             ;[8870] 99
                    inc       d                             ;[8871] 14
                    cp        $68                           ;[8872] fe 68
                    nop                                     ;[8874] 00
                    ld        d,d                           ;[8875] 52
                    add       b                             ;[8876] 80
                    rrca                                    ;[8877] 0f
                    ld        a,(bc)                        ;[8878] 0a
                    ex        af,af'                        ;[8879] 08
                    ld        (bc),a                        ;[887a] 02
                    add       c                             ;[887b] 81
                    ld        c,$3d                         ;[887c] 0e 3d
                    ret       nz                            ;[887e] c0
                    adc       e                             ;[887f] 8b
                    djnz      $8892                         ;[8880] 10 10
                    out       ($fe),a                       ;[8882] d3 fe
                    and       a                             ;[8884] a7
                    adc       c                             ;[8885] 89
                    dec       c                             ;[8886] 0d
                    sub       b                             ;[8887] 90
                    ret       nz                            ;[8888] c0
                    cp        (hl)                          ;[8889] be
                    ld        c,l                           ;[888a] 4d
                    cp        $b9                           ;[888b] fe b9
                    dec       c                             ;[888d] 0d
                    ret       nz                            ;[888e] c0
                    add       hl,hl                         ;[888f] 29
                    ld        bc,$d40e                      ;[8890] 01 0e d4
                    ld        a,c                           ;[8893] 79
                    add       b                             ;[8894] 80
                    ld        c,a                           ;[8895] 4f
                    jp        nc,$17bf                      ;[8896] d2 bf 17
                    cp        $c6                           ;[8899] fe c6
                    ret       nz                            ;[889b] c0
                    jr        nz,$88d6                      ;[889c] 20 38
                    ex        af,af'                        ;[889e] 08
                    ld        c,l                           ;[889f] 4d
                    rst       $10                           ;[88a0] d7
                    add       b                             ;[88a1] 80
                    rst       $18                           ;[88a2] df
                    ld        b,(hl)                        ;[88a3] 46
                    ret       nz                            ;[88a4] c0
                    ld        a,e                           ;[88a5] 7b
                    or        $85                           ;[88a6] f6 85
                    ret       nz                            ;[88a8] c0
                    ret       m                             ;[88a9] f8
                    push      de                            ;[88aa] d5
                    ret       nz                            ;[88ab] c0
                    adc       b                             ;[88ac] 88
                    rlca                                    ;[88ad] 07
                    rlca                                    ;[88ae] 07
                    ld        d,d                           ;[88af] 52
                    ld        h,l                           ;[88b0] 65
                    rlca                                    ;[88b1] 07
                    rlca                                    ;[88b2] 07
                    rlca                                    ;[88b3] 07
                    cp        $fe                           ;[88b4] fe fe
                    ret       nz                            ;[88b6] c0
                    inc       b                             ;[88b7] 04
                    ld        l,l                           ;[88b8] 6d
                    djnz      $889a                         ;[88b9] 10 df
                    inc       a                             ;[88bb] 3c
                    ret       nz                            ;[88bc] c0
                    ret       p                             ;[88bd] f0
                    cp        $fa                           ;[88be] fe fa
                    ret       nz                            ;[88c0] c0
                    rrca                                    ;[88c1] 0f
                    inc       bc                            ;[88c2] 03
                    rst       $28                           ;[88c3] ef
                    ret       pe                            ;[88c4] e8
                    ld        a,(bc)                        ;[88c5] 0a
                    ret       nz                            ;[88c6] c0
                    ld        c,d                           ;[88c7] 4a
                    ld        a,(bc)                        ;[88c8] 0a
                    rst       $18                           ;[88c9] df
                    dec       bc                            ;[88ca] 0b
                    cp        $79                           ;[88cb] fe 79
                    jp        c,$c0f5                       ;[88cd] da f5 c0
                    and       h                             ;[88d0] a4
                    ex        af,af'                        ;[88d1] 08
                    ld        e,a                           ;[88d2] 5f
                    cp        $8a                           ;[88d3] fe 8a
                    ex        af,af'                        ;[88d5] 08
                    jr        z,$886a                       ;[88d6] 28 92
                    ex        af,af'                        ;[88d8] 08
                    add       h                             ;[88d9] 84
                    ld        (bc),a                        ;[88da] 02
                    ld        l,$06                         ;[88db] 2e 06
                    ret       nz                            ;[88dd] c0
                    ld        c,$cc                         ;[88de] 0e cc
                    inc       bc                            ;[88e0] 03
                    sbc       e                             ;[88e1] 9b
                    jp        p,$d008                       ;[88e2] f2 08 d0
                    add       d                             ;[88e5] 82
                    rst       $38                           ;[88e6] ff
                    cp        (hl)                          ;[88e7] be
                    ret       nz                            ;[88e8] c0
                    ld        b,(hl)                        ;[88e9] 46
                    dec       h                             ;[88ea] 25
                    ld        d,$02                         ;[88eb] 16 02
                    ld        c,e                           ;[88ed] 4b
                    inc       (hl)                          ;[88ee] 34
                    jp        c,$02be                       ;[88ef] da be 02
                    inc       d                             ;[88f2] 14
                    and       b                             ;[88f3] a0
                    ld        hl,$0239                      ;[88f4] 21 39 02
                    ld        e,a                           ;[88f7] 5f
                    cp        (hl)                          ;[88f8] be
                    ld        (hl),a                        ;[88f9] 77
                    sub       d                             ;[88fa] 92
                    ld        a,b                           ;[88fb] 78
                    cp        $3f                           ;[88fc] fe 3f
                    jp        (hl)                          ;[88fe] e9
                    ret       m                             ;[88ff] f8
                    push      de                            ;[8900] d5
                    ld        d,l                           ;[8901] 55
                    ld        h,b                           ;[8902] 60
                    nop                                     ;[8903] 00
                    nop                                     ;[8904] 00
                    nop                                     ;[8905] 00
                    nop                                     ;[8906] 00
                    nop                                     ;[8907] 00
                    nop                                     ;[8908] 00
                    nop                                     ;[8909] 00
                    nop                                     ;[890a] 00
                    nop                                     ;[890b] 00
                    nop                                     ;[890c] 00
                    nop                                     ;[890d] 00
                    nop                                     ;[890e] 00
                    nop                                     ;[890f] 00
                    nop                                     ;[8910] 00
                    nop                                     ;[8911] 00
                    nop                                     ;[8912] 00
                    nop                                     ;[8913] 00
                    nop                                     ;[8914] 00
                    nop                                     ;[8915] 00
                    nop                                     ;[8916] 00
                    nop                                     ;[8917] 00
                    nop                                     ;[8918] 00
                    nop                                     ;[8919] 00
                    nop                                     ;[891a] 00
                    nop                                     ;[891b] 00
                    nop                                     ;[891c] 00
                    nop                                     ;[891d] 00
                    nop                                     ;[891e] 00
                    nop                                     ;[891f] 00
                    nop                                     ;[8920] 00
                    nop                                     ;[8921] 00
                    nop                                     ;[8922] 00
                    nop                                     ;[8923] 00
                    nop                                     ;[8924] 00
                    nop                                     ;[8925] 00
                    nop                                     ;[8926] 00
                    nop                                     ;[8927] 00
                    nop                                     ;[8928] 00
                    nop                                     ;[8929] 00
                    nop                                     ;[892a] 00
                    nop                                     ;[892b] 00
                    nop                                     ;[892c] 00
                    nop                                     ;[892d] 00
                    nop                                     ;[892e] 00
                    nop                                     ;[892f] 00
                    nop                                     ;[8930] 00
                    nop                                     ;[8931] 00
                    nop                                     ;[8932] 00
                    nop                                     ;[8933] 00
                    nop                                     ;[8934] 00
                    nop                                     ;[8935] 00
                    nop                                     ;[8936] 00
                    nop                                     ;[8937] 00
                    nop                                     ;[8938] 00
                    nop                                     ;[8939] 00
                    nop                                     ;[893a] 00
                    nop                                     ;[893b] 00
                    nop                                     ;[893c] 00
                    nop                                     ;[893d] 00
                    nop                                     ;[893e] 00
                    nop                                     ;[893f] 00
                    nop                                     ;[8940] 00
                    nop                                     ;[8941] 00
                    nop                                     ;[8942] 00
                    nop                                     ;[8943] 00
                    nop                                     ;[8944] 00
                    nop                                     ;[8945] 00
                    nop                                     ;[8946] 00
                    nop                                     ;[8947] 00
                    nop                                     ;[8948] 00
                    nop                                     ;[8949] 00
                    nop                                     ;[894a] 00
                    nop                                     ;[894b] 00
                    nop                                     ;[894c] 00
                    nop                                     ;[894d] 00
                    nop                                     ;[894e] 00
                    nop                                     ;[894f] 00
                    nop                                     ;[8950] 00
                    nop                                     ;[8951] 00
                    nop                                     ;[8952] 00
                    nop                                     ;[8953] 00
                    nop                                     ;[8954] 00
                    nop                                     ;[8955] 00
                    nop                                     ;[8956] 00
                    nop                                     ;[8957] 00
                    nop                                     ;[8958] 00
                    nop                                     ;[8959] 00
                    nop                                     ;[895a] 00
                    nop                                     ;[895b] 00
                    nop                                     ;[895c] 00
                    nop                                     ;[895d] 00
                    nop                                     ;[895e] 00
                    nop                                     ;[895f] 00
                    nop                                     ;[8960] 00
                    nop                                     ;[8961] 00
                    nop                                     ;[8962] 00
                    nop                                     ;[8963] 00
                    nop                                     ;[8964] 00
                    nop                                     ;[8965] 00
                    nop                                     ;[8966] 00
                    nop                                     ;[8967] 00
                    nop                                     ;[8968] 00
                    nop                                     ;[8969] 00
                    nop                                     ;[896a] 00
                    nop                                     ;[896b] 00
                    nop                                     ;[896c] 00
                    nop                                     ;[896d] 00
                    nop                                     ;[896e] 00
                    nop                                     ;[896f] 00
                    nop                                     ;[8970] 00
                    nop                                     ;[8971] 00
                    nop                                     ;[8972] 00
                    nop                                     ;[8973] 00
                    nop                                     ;[8974] 00
                    nop                                     ;[8975] 00
                    nop                                     ;[8976] 00
                    nop                                     ;[8977] 00
                    nop                                     ;[8978] 00
                    nop                                     ;[8979] 00
                    nop                                     ;[897a] 00
                    nop                                     ;[897b] 00
                    nop                                     ;[897c] 00
                    nop                                     ;[897d] 00
                    nop                                     ;[897e] 00
                    nop                                     ;[897f] 00
                    nop                                     ;[8980] 00
                    nop                                     ;[8981] 00
                    nop                                     ;[8982] 00
                    nop                                     ;[8983] 00
                    nop                                     ;[8984] 00
                    nop                                     ;[8985] 00
                    nop                                     ;[8986] 00
                    nop                                     ;[8987] 00
                    nop                                     ;[8988] 00
                    nop                                     ;[8989] 00
                    nop                                     ;[898a] 00
                    nop                                     ;[898b] 00
                    nop                                     ;[898c] 00
                    nop                                     ;[898d] 00
                    nop                                     ;[898e] 00
                    nop                                     ;[898f] 00
                    nop                                     ;[8990] 00
                    nop                                     ;[8991] 00
                    nop                                     ;[8992] 00
                    nop                                     ;[8993] 00
                    nop                                     ;[8994] 00
                    nop                                     ;[8995] 00
                    nop                                     ;[8996] 00
                    nop                                     ;[8997] 00
                    nop                                     ;[8998] 00
                    nop                                     ;[8999] 00
                    nop                                     ;[899a] 00
                    nop                                     ;[899b] 00
                    nop                                     ;[899c] 00
                    nop                                     ;[899d] 00
                    nop                                     ;[899e] 00
                    nop                                     ;[899f] 00
                    nop                                     ;[89a0] 00
                    nop                                     ;[89a1] 00
                    nop                                     ;[89a2] 00
                    nop                                     ;[89a3] 00
                    nop                                     ;[89a4] 00
                    nop                                     ;[89a5] 00
                    nop                                     ;[89a6] 00
                    nop                                     ;[89a7] 00
                    nop                                     ;[89a8] 00
                    nop                                     ;[89a9] 00
                    nop                                     ;[89aa] 00
                    nop                                     ;[89ab] 00
                    nop                                     ;[89ac] 00
                    nop                                     ;[89ad] 00
                    nop                                     ;[89ae] 00
                    nop                                     ;[89af] 00
                    nop                                     ;[89b0] 00
                    nop                                     ;[89b1] 00
                    nop                                     ;[89b2] 00
                    nop                                     ;[89b3] 00
                    nop                                     ;[89b4] 00
                    nop                                     ;[89b5] 00
                    nop                                     ;[89b6] 00
                    nop                                     ;[89b7] 00
                    nop                                     ;[89b8] 00
                    nop                                     ;[89b9] 00
                    nop                                     ;[89ba] 00
                    nop                                     ;[89bb] 00
                    nop                                     ;[89bc] 00
                    nop                                     ;[89bd] 00
                    nop                                     ;[89be] 00
                    nop                                     ;[89bf] 00
                    nop                                     ;[89c0] 00
                    nop                                     ;[89c1] 00
                    nop                                     ;[89c2] 00
                    nop                                     ;[89c3] 00
                    nop                                     ;[89c4] 00
                    nop                                     ;[89c5] 00
                    nop                                     ;[89c6] 00
                    nop                                     ;[89c7] 00
                    nop                                     ;[89c8] 00
                    nop                                     ;[89c9] 00
                    nop                                     ;[89ca] 00
                    nop                                     ;[89cb] 00
                    nop                                     ;[89cc] 00
                    nop                                     ;[89cd] 00
                    nop                                     ;[89ce] 00
                    nop                                     ;[89cf] 00
                    nop                                     ;[89d0] 00
                    nop                                     ;[89d1] 00
                    nop                                     ;[89d2] 00
                    nop                                     ;[89d3] 00
                    nop                                     ;[89d4] 00
                    nop                                     ;[89d5] 00
                    nop                                     ;[89d6] 00
                    nop                                     ;[89d7] 00
                    nop                                     ;[89d8] 00
                    nop                                     ;[89d9] 00
                    nop                                     ;[89da] 00
                    nop                                     ;[89db] 00
                    nop                                     ;[89dc] 00
                    nop                                     ;[89dd] 00
                    nop                                     ;[89de] 00
                    nop                                     ;[89df] 00
                    nop                                     ;[89e0] 00
                    nop                                     ;[89e1] 00
                    nop                                     ;[89e2] 00
                    nop                                     ;[89e3] 00
                    nop                                     ;[89e4] 00
                    nop                                     ;[89e5] 00
                    nop                                     ;[89e6] 00
                    nop                                     ;[89e7] 00
                    nop                                     ;[89e8] 00
                    nop                                     ;[89e9] 00
                    nop                                     ;[89ea] 00
                    nop                                     ;[89eb] 00
                    nop                                     ;[89ec] 00
                    nop                                     ;[89ed] 00
                    nop                                     ;[89ee] 00
                    nop                                     ;[89ef] 00
                    nop                                     ;[89f0] 00
                    nop                                     ;[89f1] 00
                    nop                                     ;[89f2] 00
                    nop                                     ;[89f3] 00
                    nop                                     ;[89f4] 00
                    nop                                     ;[89f5] 00
                    nop                                     ;[89f6] 00
                    nop                                     ;[89f7] 00
                    nop                                     ;[89f8] 00
                    nop                                     ;[89f9] 00
                    nop                                     ;[89fa] 00
                    nop                                     ;[89fb] 00
                    nop                                     ;[89fc] 00
                    nop                                     ;[89fd] 00
                    nop                                     ;[89fe] 00
                    nop                                     ;[89ff] 00
