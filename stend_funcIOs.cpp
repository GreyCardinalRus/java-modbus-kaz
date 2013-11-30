// =======================================================================
//                     Module stend_funcIOs.cpp
//  Author:  Komapob M.B.   Created:    08/08/2013 1:27
//
// =======================================================================

WORD TForm1::get_i ( char *v_ss, char v_dlm, WORD v_n )
// ==================================================================
// �������� �����  ����� �� ������ [2,3,1000]
// ����  :
//
// ����� :
//
{   char w_ss[SZss];

    Elems       ( v_ss, w_ss, v_dlm, v_n );
    DelSpace    ( w_ss );
    return atoi ( w_ss );
}

bool TForm1::Execute_WR_DA5XX_CC ( void )
// ==================================================================
// ������������� ������������������ ������ ������ � �����.���������.
// ����  :
//   m_uu - ����� �� ������ [! WR_DA5XX_CC = 113 !]
// ����� :
//
{   WORD w_i, w_j, w_nCmd, w_nRep, w_dly,  w_stPrm, w_nPrm, w_aReg, w_ii;
    char w_ss [ SZss ], w_ss2[ SZss ];

    Elems ( m_uu, w_ss, '!', 1 );
    DelSpace ( w_ss );             // [ WR_DA5XX_CC = 113 ]

    Elems ( w_ss, w_ss, '=', 1 );
    DelSpace ( w_ss );             // [ 113 ]

    w_nCmd = atoi ( w_ss );
    // � � � � � � � � � �   ��������� ���-�� ������.
    for ( w_i = 0 ; w_i < w_nCmd ;  ) {
        if ( ! fgets ( m_uu, SZss, m_hfPr ) )  {
            //!!! ����� �� ����� �����
            AExit ( w_ss, "Err_01_WR_DA5XX_CC" );  }
        if ( VfyCmdStop ( m_uu ) ) {
            //!!! ��������� �������������
            AExit ( w_ss, "Err_02_WR_DA5XX_CC" );  }

        if ( m_uu [ 0 ] != '!' ) {
            continue;            }
        StrUpper ( m_uu );         //########

        strcpy ( m_uu_tbl [ w_i++ ], m_uu );
    }

    // ������ ���� ����� ���������� �� DA5XX --------------
    // ������� ������ ���������
    // 1k   P001-P025(30) ��������� [100 ... 100 + NUM_PRM].
    // 5k   P001-P199()
    // ���������� ��� ���������.
    w_stPrm = FindDefData ( "P001" );
    if ( w_stPrm == 9999 ) {
        AExit ( w_ss, "Err41_9999_P001" );  }
    w_nPrm  = FindDefData ( "NUM_PRM" );
    if ( w_nPrm == 9999 ) {
        AExit ( w_ss, "Err41_9999_NUM_PRM" );  }
//      w_ii = w_aReg - w_stPrm;   // ����� ������������.

    mb_IO2_034 ( 1, 3, w_stPrm, w_nPrm );

    // ������������� byte-����� => WORD-�����.
    for ( w_i = 0 ; w_i < w_nPrm ; w_i++  ) {
        m_prm [ w_i ]  = (WORD)(com1_bf_rx[w_i*2+4]&0xff);
        m_prm [ w_i ] += (WORD)(com1_bf_rx[w_i*2+4-1]&0xff)<<8;
    }

    // ���������� �� ������ ������ � �������� ����������.
    for ( w_i = 0 ; w_i < w_nCmd ; w_i++ ) {
        strcpy ( w_ss2, m_uu_tbl [ w_i] );

        // �������� ����� ��������� � ������.
        Elems    ( w_ss2, w_ss, '!', 3 );
        DelSpace ( w_ss );
        w_aReg = FindDefData ( w_ss );
        if ( w_aReg == 9999 ) {
            AExit ( w_ss, "Err2_9999_02" );      }

            w_ii = w_aReg - w_stPrm;   // ����� ������������.
            Elems    ( w_ss2, w_ss, '!', 4 );
            DelSpace ( w_ss );
            m_prm [ w_ii ] = atoi ( w_ss );
    }
    // ----------------------------------------------------

#if DBG_MODBUS_01 == DEBUG_ON
    w_ii = mb_IO2_16 ( 1, w_stPrm, w_nPrm, m_prm, 1 ); // 0-normal Modbus
#else
    w_ii = mb_IO2_16 ( 1, w_stPrm, w_nPrm, m_prm, 0 ); // 0-������ Modbus
#endif                             //#if D-G_MODBUS_01
    PrMemo ( m_msgTest_ok );  // ���� ����� - ���� �������.

    return w_ii;
}


bool TForm1::Execute_Pr_CC ( void )
// ==================================================================
// ������������� ������������������ ������ ������ � �����.���������.
// ����  :
//   m_uu - ����� �� ������ ! VCC_01! 4, 3, 1000   !
// ����� :
//
{   WORD w_i, w_j, w_nCmd, w_nRep, w_dly;
    char w_ss [ SZss ], w_ss2[ SZss ];

    Elems ( m_uu, w_ss, '!', 2 );
    DelSpace ( w_ss );             // [! 4, 3, 1000   !]

    w_nCmd = get_i ( w_ss, ',', 0 );
    w_nRep = get_i ( w_ss, ',', 1 );
    w_dly  = get_i ( w_ss, ',', 2 );

    // ���������� ��������� ���-�� ������.
    for ( w_i = 0 ; w_i < w_nCmd ;  ) {
        if ( ! fgets ( m_uu, SZss, m_hfPr ) )  {
            AExit ( w_ss, "Err_VCC_01" );  } //!!! ����� �� ����� �����
        if ( VfyCmdStop ( m_uu ) ) {
            AExit ( w_ss, "Err_VCC_01" );  } //!!! ��������� �������������

        if ( m_uu [ 0 ] != '!' ) {
            continue;            }
        StrUpper ( m_uu );         //########

        strcpy ( m_uu_tbl [ w_i++ ], m_uu );
    }

    // ������������ ������� ������.
    for ( w_i = 0 ; w_i < w_nRep ; w_i++ )     {
        for ( w_j = 0 ; w_j < w_nCmd ; w_j++ ) {
            strcpy ( m_uu, m_uu_tbl[w_j] );
            Execute_Pr( );
        }
        Sleep ( w_dly );
    }
}

bool TForm1::Execute_Pr ( void )
// ==================================================================
// ������������� m_uu.
// ����  :
//   m_uu - ��������� ������ � ��������.
// ����� :
//
{   WORD w_i, w_ii, w_aReg, w_data[1024]; char w_ss [ SZss ], w_ss2[ SZss ];
    WORD w_func;
    WORD w_nRep, w_nDly, w_res;
    WORD w_stPrm, w_nPrm;
    WORD w_dbg1, w_dat, w_dbg2; //### ��-�� scanf
//  DWORD ww1, w_dw, ww2;
    long ww1, w_dw, ww2;
//  AnsiString w_as;   w_as = w_ss;

    Elems ( m_uu, w_ss, '!', 1 );
//  StrUpper ( m_uu );
    DelSpace ( w_ss );

    //! DELAY ! 1000      !            !     !    // ����� � ms.
    if ( strncmp ( "DELAY", w_ss, 5 ) == 0 ) {
        Elems ( m_uu, w_ss, '!', 2 );
        DelSpace ( w_ss );
        w_ii = atoi( w_ss );
        Sleep ( w_ii );
    }

    //### [STEND]
    if ( strncmp ( "STEND", w_ss, 5 ) == 0 ) {
        Elems    ( m_uu, w_ss, '!', 2 );
        DelSpace ( w_ss );

// ����������� ��������� � ���������� � ��������� ����� ��������� �� N ms.
//  VC_CCN=5, 1000 ��������� 5 ���, � ������ 1000 ms
//  ��� ������������ ������ ������������ - exit(0), ����� � log-����.
//! STEND ! MB_FUN_03 ! mb_PINK12  ! VC_CCD=5,1000 ! 0x0005 !
        if ( strncmp ( "MB_FUN_03", w_ss, 9 ) == 0 ) {
            Elems    ( m_uu, w_ss, '!', 3 );
            DelSpace ( w_ss );
            w_aReg = FindDefData ( w_ss );     // �������� ����� ��������.
            if ( w_aReg == 9999 ) {
                AExit ( w_ss, "Err1_9999_03_01" );  }

            Elems    ( m_uu, w_ss, '!', 4 );   //  [VC_CCD=5,1000]
            DelSpace ( w_ss );                 //
            if ( strncmp ( "VC_CCD", w_ss, 6 ) == 0 ) {

                Elems ( w_ss, w_ss2, '=', 1 );    //  w_ss2=[5,1000].
                w_nRep = get_i ( w_ss2, ',', 0 );
                w_nDly = get_i ( w_ss2, ',', 1 );

                Elems    ( m_uu, w_ss, '!', 5 );   //  [ 0x0005 ]
                DelSpace ( w_ss );                 //
                if ( CountChar ( w_ss, 'X' ) ) {
                    sscanf ( w_ss, "%X", &w_dw  );
                    w_dat = (WORD)w_dw;
                }
                else {
                    w_dat = atoi ( w_ss );           }

                for ( w_i = 0 ; w_i < w_nRep ; w_i++ ) {
                    mb_IO1_034 ( 1, 3, w_aReg, 1 );
                    // ��������� ���-� � com1_rx_bf[].
                    w_res  = (WORD)(com1_bf_rx[4]&0xff);
                    w_res += (WORD)(com1_bf_rx[3]&0xff)<<8;
                    if ( w_res != w_dat ) {
                        sprintf ( w_ss, "�� ������� etl=%#d %#d",
                          w_dat, w_res);
fprintf ( fdbg,"%s", m_uu );
fprintf ( fdbg,"%s", w_ss );

ClearString ( gl_ss, sizeof(gl_ss) );
SPrint_01 ( gl_ss, com1_bf_rx, 45 );
fprintf ( fdbg,"%s", gl_ss );
fflush  ( fdbg );
                        AExit ( w_ss, m_msgTest_err );  }

                    Sleep ( w_nDly );
                }
                PrMemo ( m_msgTest_ok );  // ���� ����� - ���� �������.
            }
        }

        if ( strncmp ( "MB_FUN_10", w_ss, 9 ) == 0 ) {
            Elems    ( m_uu, w_ss, '!', 3 );
            DelSpace ( w_ss );
            w_aReg = FindDefData ( w_ss );
            if ( w_aReg == 9999 ) {
                AExit ( w_ss, "Err1_9999_10_01" );    }

            Elems    ( m_uu, w_ss, '!', 4 );
            DelSpace ( w_ss );
            w_data[0] = atoi ( w_ss );
            mb_IO1_16 ( 1, w_aReg, 1, w_data );       //
            PrMemo ( m_msgTest_ok );  // ���� ����� - ���� �������.
        }
    }

    //### [DA5XX]
    if ( strncmp ( "DA5XX", w_ss, 5 ) == 0 ) {
        Elems    ( m_uu, w_ss, '!', 2 );
        DelSpace ( w_ss );

// ����������� "DA5XX"
//   func = 0x03 ��� P001-P025(30) ��������� [100 ... 100 + NUM_PRM].
//   func = 0x04 ��� DA_I1A ... DA_RELE
//???
// ����������� ��������� � ���������� � ��������� ����� ��������� �� N ms.
//  VC_CCN=5, 1000 ��������� 5 ���, � ������ 1000 ms
//  ��� ������������ ������ ������������ - exit(0), ����� � log-����.
//! DA5XX ! MB_FUN_04 ! DA_ALR1    ! VC_CCD=1,1    ! 0x0000 !
        w_func = 0;
        if ( strncmp ( "MB_FUN_03", w_ss, 9 ) == 0 ) {
            w_func = 3; }
        if ( strncmp ( "MB_FUN_04", w_ss, 9 ) == 0 ) {
            w_func = 4; }
        if (( w_func == 3 )|( w_func == 4 )) {
            Elems    ( m_uu, w_ss, '!', 3 );
            DelSpace ( w_ss );
            w_aReg = FindDefData ( w_ss );     // �������� ����� ��������.
            if ( w_aReg == 9999 ) {
                AExit ( w_ss, "Err2_9999_04_01" );  }

            Elems    ( m_uu, w_ss, '!', 4 );   //  [VC_CCD=5,1000]
            DelSpace ( w_ss );                 //
            if ( strncmp ( "VC_CCD", w_ss, 6 ) == 0 ) {

                Elems ( w_ss, w_ss2, '=', 1 );    //  w_ss2=[5,1000].
                w_nRep = get_i ( w_ss2, ',', 0 );
                w_nDly = get_i ( w_ss2, ',', 1 );

                Elems    ( m_uu, w_ss, '!', 5 );   //  [ 0x0005 ]
                DelSpace ( w_ss );                 //
                if ( CountChar ( w_ss, 'X' ) ) {
//StrToHex
                    sscanf ( w_ss, "%X", &w_dw  );
                    w_dat = (WORD)w_dw;
                }
                else {
                    w_dat = atoi ( w_ss );           }

                for ( w_i = 0 ; w_i < w_nRep ; w_i++ ) {
                    // ���������� ����� �������.
if ( w_func == 3 ) {
//   func = 0x03 ��� P001-P025(30) ��������� [100 ... 100 + NUM_PRM].
                    w_stPrm = FindDefData ( "P001" );
                    if ( w_stPrm == 9999 ) {
                        AExit ( w_ss, "Err2_9999_P001" );  }
                    w_nPrm  = FindDefData ( "NUM_PRM" );
                    if ( w_nPrm == 9999 ) {
                        AExit ( w_ss, "Err2_9999_NUM_PRM" );  }
                    w_ii = w_aReg - w_stPrm;   // ����� ������������.
                    mb_IO2_034 ( 1, w_func, w_stPrm, w_nPrm );
}
if ( w_func == 4 ) {
//#define !DA_I1A      !2     !//
// ...
//#define !DA_RELE     !21    !// 0x15
//
                    w_stPrm = FindDefData ( "DA_I1A" );
                    if ( w_stPrm == 9999 ) {
                        AExit ( w_ss, "Err2_9999_DA_I1A" );  }

                    w_nPrm  = FindDefData ( "DA_RELE" );
                    if ( w_nPrm == 9999 ) {
                        AExit ( w_ss, "Err2_9999_DA_RELE" );  }

                    w_ii = w_aReg - w_stPrm;   // ����� ������-�� � ������.
//                  w_i  = w_nPrm - w_stPrm + 1;
//                  mb_IO2_034 ( 1, w_func, w_stPrm, w_i );
#if DBG_01 == DEBUG_ON                 // ��� 5k - 96
                    w_nPrm--;
                    mb_IO2_034 ( 1, w_func, w_stPrm, w_nPrm );
#else
                    mb_IO2_034 ( 1, w_func, w_stPrm, 20 );
#endif                                 // #if D-G_01 == DEBUG_ON
}
                    // ��������� ���-� � com1_rx_bf[].
                    w_res  = (WORD)(com2_bf_rx[w_ii*2+4]&0xff);
                    w_res += (WORD)(com2_bf_rx[w_ii*2+4-1]&0xff)<<8;

                    if ( w_res != w_dat ) {
                        sprintf ( w_ss, "�� ������� etl=%#d %#d",
                          w_dat, w_res);
fprintf ( fdbg,"%s", m_uu );
fprintf ( fdbg,"%s", w_ss );

ClearString ( gl_ss, sizeof(gl_ss) );
SPrint_01 ( gl_ss, com2_bf_rx, w_nPrm*2+5 );
fprintf ( fdbg,"%s", gl_ss );
fflush  ( fdbg );
                        AExit ( w_ss, m_msgTest_err );  }
                    Sleep ( w_nDly );
                }
                PrMemo ( m_msgTest_ok );  // ���� ����� - ���� �������.
            }
        }

        if ( strncmp ( "MB_FUN_10", w_ss, 9 ) == 0 ) {
// ������� ������ ���������
// 1k   P001-P025(30) ��������� [100 ... 100 + NUM_PRM].
// 5k   P001-P199()
            // ���������� ��� ���������.
            w_stPrm = FindDefData ( "P001" );
            if ( w_stPrm == 9999 ) {
                AExit ( w_ss, "Err21_9999_P001" );  }
            w_nPrm  = FindDefData ( "NUM_PRM" );
            if ( w_nPrm == 9999 ) {
                AExit ( w_ss, "Err21_9999_NUM_PRM" );  }
//          w_ii = w_aReg - w_stPrm;   // ����� ������������.

#if DBG_01 != DEBUG_ON                 // ��� 5k - 117
            w_nPrm = 29;               //#################################
#endif                                 // #if D-G_01 == DEBUG_ON

            mb_IO2_034 ( 1, 3, w_stPrm, w_nPrm );

            // ������������� byte-����� => WORD-�����.
            for ( w_i = 0 ; w_i < w_nPrm ; w_i++  ) {
                m_prm [ w_i ]  = (WORD)(com2_bf_rx[w_i*2+4]&0xff);
                m_prm [ w_i ] += (WORD)(com2_bf_rx[w_i*2+4-1]&0xff)<<8;
            }

//! DA5XX ! MB_FUN_10 ! P002 ! 20  !  //  ���-�� �������� ����������.
            Elems    ( m_uu, w_ss, '!', 3 );
            DelSpace ( w_ss );
            w_aReg = FindDefData ( w_ss );
            if ( w_aReg == 9999 ) {
                AExit ( w_ss, "Err2_9999_02" );      }

            w_ii = w_aReg - w_stPrm;   // ����� ������������.
            Elems    ( m_uu, w_ss, '!', 4 );
            DelSpace ( w_ss );
            m_prm [ w_ii ] = atoi ( w_ss );

#if DBG_MODBUS_01 == DEBUG_ON
            mb_IO2_16 ( 1, w_stPrm, w_nPrm, m_prm, 1 ); // 1-normal Modbus
#else
            mb_IO2_16 ( 1, w_stPrm, w_nPrm, m_prm, 0 ); // 0-������ Modbus
#endif                             //#if D-G_MODBUS_01
            PrMemo ( m_msgTest_ok );  // ���� ����� - ���� �������.
        }
    }
}

