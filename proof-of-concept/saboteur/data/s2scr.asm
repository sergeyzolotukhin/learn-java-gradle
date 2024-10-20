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
                    add       hl,de                         ;[8036] 19                          HL <- HL + DE
                    ldir                                    ;[8037] ed b0                       (DE) <- (HL), DE <- DE+1, HL <- HL+1, BC <- BC-1} while (BC != 0)
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

; subprogram (read/write -> a, bc, hl)
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

                    add       a                             ;[8070] 87          a <- a + a
                    rl        c                             ;[8071] cb 11       c <- c * 2
                    add       a                             ;[8073] 87          a <- a + a
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

