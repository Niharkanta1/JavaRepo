package net.miginfocom.layout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class ConstraintParser {
  public static LC parseLayoutConstraint(String paramString) { // Byte code:
    //   0: new net/miginfocom/layout/LC
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokevirtual length : ()I
    //   12: ifne -> 17
    //   15: aload_1
    //   16: areturn
    //   17: aload_0
    //   18: bipush #44
    //   20: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   23: astore_2
    //   24: iconst_0
    //   25: istore_3
    //   26: iload_3
    //   27: aload_2
    //   28: arraylength
    //   29: if_icmpge -> 203
    //   32: aload_2
    //   33: iload_3
    //   34: aaload
    //   35: astore #4
    //   37: aload #4
    //   39: ifnonnull -> 45
    //   42: goto -> 197
    //   45: aload #4
    //   47: invokevirtual length : ()I
    //   50: istore #5
    //   52: iload #5
    //   54: iconst_3
    //   55: if_icmpeq -> 65
    //   58: iload #5
    //   60: bipush #11
    //   62: if_icmpne -> 197
    //   65: aload #4
    //   67: ldc 'ltr'
    //   69: invokevirtual equals : (Ljava/lang/Object;)Z
    //   72: ifne -> 105
    //   75: aload #4
    //   77: ldc 'rtl'
    //   79: invokevirtual equals : (Ljava/lang/Object;)Z
    //   82: ifne -> 105
    //   85: aload #4
    //   87: ldc 'lefttoright'
    //   89: invokevirtual equals : (Ljava/lang/Object;)Z
    //   92: ifne -> 105
    //   95: aload #4
    //   97: ldc 'righttoleft'
    //   99: invokevirtual equals : (Ljava/lang/Object;)Z
    //   102: ifeq -> 133
    //   105: aload_1
    //   106: aload #4
    //   108: iconst_0
    //   109: invokevirtual charAt : (I)C
    //   112: bipush #108
    //   114: if_icmpne -> 123
    //   117: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   120: goto -> 126
    //   123: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   126: invokevirtual setLeftToRight : (Ljava/lang/Boolean;)V
    //   129: aload_2
    //   130: iload_3
    //   131: aconst_null
    //   132: aastore
    //   133: aload #4
    //   135: ldc 'ttb'
    //   137: invokevirtual equals : (Ljava/lang/Object;)Z
    //   140: ifne -> 173
    //   143: aload #4
    //   145: ldc 'btt'
    //   147: invokevirtual equals : (Ljava/lang/Object;)Z
    //   150: ifne -> 173
    //   153: aload #4
    //   155: ldc 'toptobottom'
    //   157: invokevirtual equals : (Ljava/lang/Object;)Z
    //   160: ifne -> 173
    //   163: aload #4
    //   165: ldc 'bottomtotop'
    //   167: invokevirtual equals : (Ljava/lang/Object;)Z
    //   170: ifeq -> 197
    //   173: aload_1
    //   174: aload #4
    //   176: iconst_0
    //   177: invokevirtual charAt : (I)C
    //   180: bipush #116
    //   182: if_icmpne -> 189
    //   185: iconst_1
    //   186: goto -> 190
    //   189: iconst_0
    //   190: invokevirtual setTopToBottom : (Z)V
    //   193: aload_2
    //   194: iload_3
    //   195: aconst_null
    //   196: aastore
    //   197: iinc #3, 1
    //   200: goto -> 26
    //   203: iconst_0
    //   204: istore_3
    //   205: iload_3
    //   206: aload_2
    //   207: arraylength
    //   208: if_icmpge -> 1571
    //   211: aload_2
    //   212: iload_3
    //   213: aaload
    //   214: astore #4
    //   216: aload #4
    //   218: ifnull -> 1565
    //   221: aload #4
    //   223: invokevirtual length : ()I
    //   226: ifne -> 232
    //   229: goto -> 1565
    //   232: iconst_m1
    //   233: istore #5
    //   235: aload #4
    //   237: iconst_0
    //   238: invokevirtual charAt : (I)C
    //   241: istore #6
    //   243: iload #6
    //   245: bipush #119
    //   247: if_icmpeq -> 257
    //   250: iload #6
    //   252: bipush #104
    //   254: if_icmpne -> 583
    //   257: aload #4
    //   259: ldc 'wrap'
    //   261: iconst_m1
    //   262: iconst_1
    //   263: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   266: istore #5
    //   268: iload #5
    //   270: iconst_m1
    //   271: if_icmple -> 310
    //   274: aload #4
    //   276: iload #5
    //   278: invokevirtual substring : (I)Ljava/lang/String;
    //   281: invokevirtual trim : ()Ljava/lang/String;
    //   284: astore #7
    //   286: aload_1
    //   287: aload #7
    //   289: invokevirtual length : ()I
    //   292: ifeq -> 303
    //   295: aload #7
    //   297: invokestatic parseInt : (Ljava/lang/String;)I
    //   300: goto -> 304
    //   303: iconst_0
    //   304: invokevirtual setWrapAfter : (I)V
    //   307: goto -> 1565
    //   310: iload #6
    //   312: bipush #119
    //   314: if_icmpne -> 321
    //   317: iconst_1
    //   318: goto -> 322
    //   321: iconst_0
    //   322: istore #7
    //   324: iload #7
    //   326: ifeq -> 390
    //   329: aload #4
    //   331: ldc 'w '
    //   333: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   336: ifne -> 349
    //   339: aload #4
    //   341: ldc 'width '
    //   343: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   346: ifeq -> 390
    //   349: aload #4
    //   351: aload #4
    //   353: iconst_1
    //   354: invokevirtual charAt : (I)C
    //   357: bipush #32
    //   359: if_icmpne -> 366
    //   362: iconst_2
    //   363: goto -> 368
    //   366: bipush #6
    //   368: invokevirtual substring : (I)Ljava/lang/String;
    //   371: invokevirtual trim : ()Ljava/lang/String;
    //   374: astore #8
    //   376: aload_1
    //   377: aload #8
    //   379: iconst_0
    //   380: iconst_1
    //   381: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   384: invokevirtual setWidth : (Lnet/miginfocom/layout/BoundSize;)V
    //   387: goto -> 1565
    //   390: iload #7
    //   392: ifne -> 456
    //   395: aload #4
    //   397: ldc 'h '
    //   399: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   402: ifne -> 415
    //   405: aload #4
    //   407: ldc 'height '
    //   409: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   412: ifeq -> 456
    //   415: aload #4
    //   417: aload #4
    //   419: iconst_1
    //   420: invokevirtual charAt : (I)C
    //   423: bipush #32
    //   425: if_icmpne -> 432
    //   428: iconst_2
    //   429: goto -> 434
    //   432: bipush #7
    //   434: invokevirtual substring : (I)Ljava/lang/String;
    //   437: invokevirtual trim : ()Ljava/lang/String;
    //   440: astore #8
    //   442: aload_1
    //   443: aload #8
    //   445: iconst_0
    //   446: iconst_0
    //   447: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   450: invokevirtual setHeight : (Lnet/miginfocom/layout/BoundSize;)V
    //   453: goto -> 1565
    //   456: aload #4
    //   458: invokevirtual length : ()I
    //   461: iconst_5
    //   462: if_icmple -> 556
    //   465: aload #4
    //   467: iconst_5
    //   468: invokevirtual substring : (I)Ljava/lang/String;
    //   471: invokevirtual trim : ()Ljava/lang/String;
    //   474: astore #8
    //   476: aload #4
    //   478: ldc 'wmin '
    //   480: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   483: ifeq -> 496
    //   486: aload_1
    //   487: aload #8
    //   489: invokevirtual minWidth : (Ljava/lang/String;)Lnet/miginfocom/layout/LC;
    //   492: pop
    //   493: goto -> 1565
    //   496: aload #4
    //   498: ldc 'wmax '
    //   500: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   503: ifeq -> 516
    //   506: aload_1
    //   507: aload #8
    //   509: invokevirtual maxWidth : (Ljava/lang/String;)Lnet/miginfocom/layout/LC;
    //   512: pop
    //   513: goto -> 1565
    //   516: aload #4
    //   518: ldc 'hmin '
    //   520: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   523: ifeq -> 536
    //   526: aload_1
    //   527: aload #8
    //   529: invokevirtual minHeight : (Ljava/lang/String;)Lnet/miginfocom/layout/LC;
    //   532: pop
    //   533: goto -> 1565
    //   536: aload #4
    //   538: ldc 'hmax '
    //   540: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   543: ifeq -> 556
    //   546: aload_1
    //   547: aload #8
    //   549: invokevirtual maxHeight : (Ljava/lang/String;)Lnet/miginfocom/layout/LC;
    //   552: pop
    //   553: goto -> 1565
    //   556: aload #4
    //   558: ldc 'hidemode '
    //   560: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   563: ifeq -> 583
    //   566: aload_1
    //   567: aload #4
    //   569: bipush #9
    //   571: invokevirtual substring : (I)Ljava/lang/String;
    //   574: invokestatic parseInt : (Ljava/lang/String;)I
    //   577: invokevirtual setHideMode : (I)V
    //   580: goto -> 1565
    //   583: iload #6
    //   585: bipush #103
    //   587: if_icmpne -> 721
    //   590: aload #4
    //   592: ldc 'gapx '
    //   594: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   597: ifeq -> 621
    //   600: aload_1
    //   601: aload #4
    //   603: iconst_5
    //   604: invokevirtual substring : (I)Ljava/lang/String;
    //   607: invokevirtual trim : ()Ljava/lang/String;
    //   610: iconst_1
    //   611: iconst_1
    //   612: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   615: invokevirtual setGridGapX : (Lnet/miginfocom/layout/BoundSize;)V
    //   618: goto -> 1565
    //   621: aload #4
    //   623: ldc 'gapy '
    //   625: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   628: ifeq -> 652
    //   631: aload_1
    //   632: aload #4
    //   634: iconst_5
    //   635: invokevirtual substring : (I)Ljava/lang/String;
    //   638: invokevirtual trim : ()Ljava/lang/String;
    //   641: iconst_1
    //   642: iconst_0
    //   643: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   646: invokevirtual setGridGapY : (Lnet/miginfocom/layout/BoundSize;)V
    //   649: goto -> 1565
    //   652: aload #4
    //   654: ldc 'gap '
    //   656: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   659: ifeq -> 721
    //   662: aload #4
    //   664: iconst_4
    //   665: invokevirtual substring : (I)Ljava/lang/String;
    //   668: invokevirtual trim : ()Ljava/lang/String;
    //   671: bipush #32
    //   673: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   676: astore #7
    //   678: aload_1
    //   679: aload #7
    //   681: iconst_0
    //   682: aaload
    //   683: iconst_1
    //   684: iconst_1
    //   685: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   688: invokevirtual setGridGapX : (Lnet/miginfocom/layout/BoundSize;)V
    //   691: aload_1
    //   692: aload #7
    //   694: arraylength
    //   695: iconst_1
    //   696: if_icmple -> 711
    //   699: aload #7
    //   701: iconst_1
    //   702: aaload
    //   703: iconst_1
    //   704: iconst_0
    //   705: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   708: goto -> 715
    //   711: aload_1
    //   712: invokevirtual getGridGapX : ()Lnet/miginfocom/layout/BoundSize;
    //   715: invokevirtual setGridGapY : (Lnet/miginfocom/layout/BoundSize;)V
    //   718: goto -> 1565
    //   721: iload #6
    //   723: bipush #100
    //   725: if_icmpne -> 783
    //   728: aload #4
    //   730: ldc 'debug'
    //   732: iconst_5
    //   733: iconst_1
    //   734: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   737: istore #5
    //   739: iload #5
    //   741: iconst_m1
    //   742: if_icmple -> 783
    //   745: aload #4
    //   747: iload #5
    //   749: invokevirtual substring : (I)Ljava/lang/String;
    //   752: invokevirtual trim : ()Ljava/lang/String;
    //   755: astore #7
    //   757: aload_1
    //   758: aload #7
    //   760: invokevirtual length : ()I
    //   763: ifle -> 774
    //   766: aload #7
    //   768: invokestatic parseInt : (Ljava/lang/String;)I
    //   771: goto -> 777
    //   774: sipush #1000
    //   777: invokevirtual setDebugMillis : (I)V
    //   780: goto -> 1565
    //   783: iload #6
    //   785: bipush #110
    //   787: if_icmpne -> 844
    //   790: aload #4
    //   792: ldc 'nogrid'
    //   794: invokevirtual equals : (Ljava/lang/Object;)Z
    //   797: ifeq -> 808
    //   800: aload_1
    //   801: iconst_1
    //   802: invokevirtual setNoGrid : (Z)V
    //   805: goto -> 1565
    //   808: aload #4
    //   810: ldc 'nocache'
    //   812: invokevirtual equals : (Ljava/lang/Object;)Z
    //   815: ifeq -> 826
    //   818: aload_1
    //   819: iconst_1
    //   820: invokevirtual setNoCache : (Z)V
    //   823: goto -> 1565
    //   826: aload #4
    //   828: ldc 'novisualpadding'
    //   830: invokevirtual equals : (Ljava/lang/Object;)Z
    //   833: ifeq -> 844
    //   836: aload_1
    //   837: iconst_0
    //   838: invokevirtual setVisualPadding : (Z)V
    //   841: goto -> 1565
    //   844: iload #6
    //   846: bipush #102
    //   848: if_icmpne -> 978
    //   851: aload #4
    //   853: ldc 'fill'
    //   855: invokevirtual equals : (Ljava/lang/Object;)Z
    //   858: ifne -> 881
    //   861: aload #4
    //   863: ldc 'fillx'
    //   865: invokevirtual equals : (Ljava/lang/Object;)Z
    //   868: ifne -> 881
    //   871: aload #4
    //   873: ldc 'filly'
    //   875: invokevirtual equals : (Ljava/lang/Object;)Z
    //   878: ifeq -> 942
    //   881: aload_1
    //   882: aload #4
    //   884: invokevirtual length : ()I
    //   887: iconst_4
    //   888: if_icmpeq -> 902
    //   891: aload #4
    //   893: iconst_4
    //   894: invokevirtual charAt : (I)C
    //   897: bipush #120
    //   899: if_icmpne -> 906
    //   902: iconst_1
    //   903: goto -> 907
    //   906: iconst_0
    //   907: invokevirtual setFillX : (Z)V
    //   910: aload_1
    //   911: aload #4
    //   913: invokevirtual length : ()I
    //   916: iconst_4
    //   917: if_icmpeq -> 931
    //   920: aload #4
    //   922: iconst_4
    //   923: invokevirtual charAt : (I)C
    //   926: bipush #121
    //   928: if_icmpne -> 935
    //   931: iconst_1
    //   932: goto -> 936
    //   935: iconst_0
    //   936: invokevirtual setFillY : (Z)V
    //   939: goto -> 1565
    //   942: aload #4
    //   944: ldc 'flowy'
    //   946: invokevirtual equals : (Ljava/lang/Object;)Z
    //   949: ifeq -> 960
    //   952: aload_1
    //   953: iconst_0
    //   954: invokevirtual setFlowX : (Z)V
    //   957: goto -> 1565
    //   960: aload #4
    //   962: ldc 'flowx'
    //   964: invokevirtual equals : (Ljava/lang/Object;)Z
    //   967: ifeq -> 978
    //   970: aload_1
    //   971: iconst_1
    //   972: invokevirtual setFlowX : (Z)V
    //   975: goto -> 1565
    //   978: iload #6
    //   980: bipush #105
    //   982: if_icmpne -> 1038
    //   985: aload #4
    //   987: ldc 'insets'
    //   989: iconst_3
    //   990: iconst_1
    //   991: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   994: istore #5
    //   996: iload #5
    //   998: iconst_m1
    //   999: if_icmple -> 1038
    //   1002: aload #4
    //   1004: iload #5
    //   1006: invokevirtual substring : (I)Ljava/lang/String;
    //   1009: invokevirtual trim : ()Ljava/lang/String;
    //   1012: astore #7
    //   1014: aload #7
    //   1016: iconst_1
    //   1017: invokestatic parseInsets : (Ljava/lang/String;Z)[Lnet/miginfocom/layout/UnitValue;
    //   1020: astore #8
    //   1022: aload #8
    //   1024: aload #7
    //   1026: invokestatic putCCString : (Ljava/lang/Object;Ljava/lang/String;)V
    //   1029: aload_1
    //   1030: aload #8
    //   1032: invokevirtual setInsets : ([Lnet/miginfocom/layout/UnitValue;)V
    //   1035: goto -> 1565
    //   1038: iload #6
    //   1040: bipush #97
    //   1042: if_icmpne -> 1268
    //   1045: aload #4
    //   1047: iconst_2
    //   1048: anewarray java/lang/String
    //   1051: dup
    //   1052: iconst_0
    //   1053: ldc 'aligny'
    //   1055: aastore
    //   1056: dup
    //   1057: iconst_1
    //   1058: ldc 'ay'
    //   1060: aastore
    //   1061: iconst_2
    //   1062: newarray int
    //   1064: dup
    //   1065: iconst_0
    //   1066: bipush #6
    //   1068: iastore
    //   1069: dup
    //   1070: iconst_1
    //   1071: iconst_2
    //   1072: iastore
    //   1073: iconst_1
    //   1074: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   1077: istore #5
    //   1079: iload #5
    //   1081: iconst_m1
    //   1082: if_icmple -> 1129
    //   1085: aload #4
    //   1087: iload #5
    //   1089: invokevirtual substring : (I)Ljava/lang/String;
    //   1092: invokevirtual trim : ()Ljava/lang/String;
    //   1095: iconst_0
    //   1096: aconst_null
    //   1097: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1100: astore #7
    //   1102: aload #7
    //   1104: getstatic net/miginfocom/layout/UnitValue.BASELINE_IDENTITY : Lnet/miginfocom/layout/UnitValue;
    //   1107: if_acmpne -> 1120
    //   1110: new java/lang/IllegalArgumentException
    //   1113: dup
    //   1114: ldc ''baseline' can not be used to align the whole component group.'
    //   1116: invokespecial <init> : (Ljava/lang/String;)V
    //   1119: athrow
    //   1120: aload_1
    //   1121: aload #7
    //   1123: invokevirtual setAlignY : (Lnet/miginfocom/layout/UnitValue;)V
    //   1126: goto -> 1565
    //   1129: aload #4
    //   1131: iconst_2
    //   1132: anewarray java/lang/String
    //   1135: dup
    //   1136: iconst_0
    //   1137: ldc 'alignx'
    //   1139: aastore
    //   1140: dup
    //   1141: iconst_1
    //   1142: ldc 'ax'
    //   1144: aastore
    //   1145: iconst_2
    //   1146: newarray int
    //   1148: dup
    //   1149: iconst_0
    //   1150: bipush #6
    //   1152: iastore
    //   1153: dup
    //   1154: iconst_1
    //   1155: iconst_2
    //   1156: iastore
    //   1157: iconst_1
    //   1158: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   1161: istore #5
    //   1163: iload #5
    //   1165: iconst_m1
    //   1166: if_icmple -> 1191
    //   1169: aload_1
    //   1170: aload #4
    //   1172: iload #5
    //   1174: invokevirtual substring : (I)Ljava/lang/String;
    //   1177: invokevirtual trim : ()Ljava/lang/String;
    //   1180: iconst_1
    //   1181: aconst_null
    //   1182: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1185: invokevirtual setAlignX : (Lnet/miginfocom/layout/UnitValue;)V
    //   1188: goto -> 1565
    //   1191: aload #4
    //   1193: ldc 'align'
    //   1195: iconst_2
    //   1196: iconst_1
    //   1197: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   1200: istore #5
    //   1202: iload #5
    //   1204: iconst_m1
    //   1205: if_icmple -> 1268
    //   1208: aload #4
    //   1210: iload #5
    //   1212: invokevirtual substring : (I)Ljava/lang/String;
    //   1215: invokevirtual trim : ()Ljava/lang/String;
    //   1218: bipush #32
    //   1220: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1223: astore #7
    //   1225: aload_1
    //   1226: aload #7
    //   1228: iconst_0
    //   1229: aaload
    //   1230: iconst_1
    //   1231: aconst_null
    //   1232: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1235: invokevirtual setAlignX : (Lnet/miginfocom/layout/UnitValue;)V
    //   1238: aload_1
    //   1239: aload #7
    //   1241: arraylength
    //   1242: iconst_1
    //   1243: if_icmple -> 1258
    //   1246: aload #7
    //   1248: iconst_1
    //   1249: aaload
    //   1250: iconst_0
    //   1251: aconst_null
    //   1252: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1255: goto -> 1262
    //   1258: aload_1
    //   1259: invokevirtual getAlignX : ()Lnet/miginfocom/layout/UnitValue;
    //   1262: invokevirtual setAlignY : (Lnet/miginfocom/layout/UnitValue;)V
    //   1265: goto -> 1565
    //   1268: iload #6
    //   1270: bipush #112
    //   1272: if_icmpne -> 1438
    //   1275: aload #4
    //   1277: ldc 'packalign '
    //   1279: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1282: ifeq -> 1349
    //   1285: aload #4
    //   1287: bipush #10
    //   1289: invokevirtual substring : (I)Ljava/lang/String;
    //   1292: invokevirtual trim : ()Ljava/lang/String;
    //   1295: bipush #32
    //   1297: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1300: astore #7
    //   1302: aload_1
    //   1303: aload #7
    //   1305: iconst_0
    //   1306: aaload
    //   1307: invokevirtual length : ()I
    //   1310: ifle -> 1323
    //   1313: aload #7
    //   1315: iconst_0
    //   1316: aaload
    //   1317: invokestatic parseFloat : (Ljava/lang/String;)F
    //   1320: goto -> 1325
    //   1323: ldc 0.5
    //   1325: invokevirtual setPackWidthAlign : (F)V
    //   1328: aload #7
    //   1330: arraylength
    //   1331: iconst_1
    //   1332: if_icmple -> 1346
    //   1335: aload_1
    //   1336: aload #7
    //   1338: iconst_1
    //   1339: aaload
    //   1340: invokestatic parseFloat : (Ljava/lang/String;)F
    //   1343: invokevirtual setPackHeightAlign : (F)V
    //   1346: goto -> 1565
    //   1349: aload #4
    //   1351: ldc 'pack '
    //   1353: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1356: ifne -> 1369
    //   1359: aload #4
    //   1361: ldc 'pack'
    //   1363: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1366: ifeq -> 1438
    //   1369: aload #4
    //   1371: iconst_4
    //   1372: invokevirtual substring : (I)Ljava/lang/String;
    //   1375: invokevirtual trim : ()Ljava/lang/String;
    //   1378: astore #7
    //   1380: aload #7
    //   1382: invokevirtual length : ()I
    //   1385: ifle -> 1393
    //   1388: aload #7
    //   1390: goto -> 1395
    //   1393: ldc 'pref pref'
    //   1395: bipush #32
    //   1397: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1400: astore #8
    //   1402: aload_1
    //   1403: aload #8
    //   1405: iconst_0
    //   1406: aaload
    //   1407: iconst_0
    //   1408: iconst_1
    //   1409: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   1412: invokevirtual setPackWidth : (Lnet/miginfocom/layout/BoundSize;)V
    //   1415: aload #8
    //   1417: arraylength
    //   1418: iconst_1
    //   1419: if_icmple -> 1435
    //   1422: aload_1
    //   1423: aload #8
    //   1425: iconst_1
    //   1426: aaload
    //   1427: iconst_0
    //   1428: iconst_0
    //   1429: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   1432: invokevirtual setPackHeight : (Lnet/miginfocom/layout/BoundSize;)V
    //   1435: goto -> 1565
    //   1438: aload_1
    //   1439: invokevirtual getAlignX : ()Lnet/miginfocom/layout/UnitValue;
    //   1442: ifnonnull -> 1467
    //   1445: aload #4
    //   1447: iconst_1
    //   1448: invokestatic parseAlignKeywords : (Ljava/lang/String;Z)Lnet/miginfocom/layout/UnitValue;
    //   1451: astore #7
    //   1453: aload #7
    //   1455: ifnull -> 1467
    //   1458: aload_1
    //   1459: aload #7
    //   1461: invokevirtual setAlignX : (Lnet/miginfocom/layout/UnitValue;)V
    //   1464: goto -> 1565
    //   1467: aload #4
    //   1469: iconst_0
    //   1470: invokestatic parseAlignKeywords : (Ljava/lang/String;Z)Lnet/miginfocom/layout/UnitValue;
    //   1473: astore #7
    //   1475: aload #7
    //   1477: ifnull -> 1489
    //   1480: aload_1
    //   1481: aload #7
    //   1483: invokevirtual setAlignY : (Lnet/miginfocom/layout/UnitValue;)V
    //   1486: goto -> 1565
    //   1489: new java/lang/IllegalArgumentException
    //   1492: dup
    //   1493: new java/lang/StringBuilder
    //   1496: dup
    //   1497: invokespecial <init> : ()V
    //   1500: ldc 'Unknown Constraint: ''
    //   1502: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1505: aload #4
    //   1507: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1510: ldc ''\\n'
    //   1512: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1515: invokevirtual toString : ()Ljava/lang/String;
    //   1518: invokespecial <init> : (Ljava/lang/String;)V
    //   1521: athrow
    //   1522: astore #5
    //   1524: new java/lang/IllegalArgumentException
    //   1527: dup
    //   1528: new java/lang/StringBuilder
    //   1531: dup
    //   1532: invokespecial <init> : ()V
    //   1535: ldc 'Illegal Constraint: ''
    //   1537: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1540: aload #4
    //   1542: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1545: ldc ''\\n'
    //   1547: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1550: aload #5
    //   1552: invokevirtual getMessage : ()Ljava/lang/String;
    //   1555: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1558: invokevirtual toString : ()Ljava/lang/String;
    //   1561: invokespecial <init> : (Ljava/lang/String;)V
    //   1564: athrow
    //   1565: iinc #3, 1
    //   1568: goto -> 205
    //   1571: aload_1
    //   1572: areturn
    // Exception table:
    //   from	to	target	type
    //   232	307	1522	java/lang/Exception
    //   310	387	1522	java/lang/Exception
    //   390	453	1522	java/lang/Exception
    //   456	493	1522	java/lang/Exception
    //   496	513	1522	java/lang/Exception
    //   516	533	1522	java/lang/Exception
    //   536	553	1522	java/lang/Exception
    //   556	580	1522	java/lang/Exception
    //   583	618	1522	java/lang/Exception
    //   621	649	1522	java/lang/Exception
    //   652	718	1522	java/lang/Exception
    //   721	780	1522	java/lang/Exception
    //   783	805	1522	java/lang/Exception
    //   808	823	1522	java/lang/Exception
    //   826	841	1522	java/lang/Exception
    //   844	939	1522	java/lang/Exception
    //   942	957	1522	java/lang/Exception
    //   960	975	1522	java/lang/Exception
    //   978	1035	1522	java/lang/Exception
    //   1038	1126	1522	java/lang/Exception
    //   1129	1188	1522	java/lang/Exception
    //   1191	1265	1522	java/lang/Exception
    //   1268	1346	1522	java/lang/Exception
    //   1349	1435	1522	java/lang/Exception
    //   1438	1464	1522	java/lang/Exception
    //   1467	1486	1522	java/lang/Exception
    //   1489	1522	1522	java/lang/Exception }
  
  public static AC parseRowConstraints(String paramString) { return parseAxisConstraint(paramString, false); }
  
  public static AC parseColumnConstraints(String paramString) { return parseAxisConstraint(paramString, true); }
  
  private static AC parseAxisConstraint(String paramString, boolean paramBoolean) {
    paramString = paramString.trim();
    if (paramString.length() == 0)
      return new AC(); 
    paramString = paramString.toLowerCase();
    ArrayList arrayList = getRowColAndGapsTrimmed(paramString);
    BoundSize[] arrayOfBoundSize = new BoundSize[(arrayList.size() >> 1) + 1];
    null = 0;
    int i = arrayList.size();
    int j;
    for (j = 0; null < i; j++) {
      arrayOfBoundSize[j] = parseBoundSize((String)arrayList.get(null), true, paramBoolean);
      null += 2;
    } 
    DimConstraint[] arrayOfDimConstraint = new DimConstraint[arrayList.size() >> 1];
    i = 0;
    for (j = 0; i < arrayOfDimConstraint.length; j++) {
      if (j >= arrayOfBoundSize.length - 1)
        j = arrayOfBoundSize.length - 2; 
      arrayOfDimConstraint[i] = parseDimConstraint((String)arrayList.get((i << 1) + 1), arrayOfBoundSize[j], arrayOfBoundSize[j + 1], paramBoolean);
      i++;
    } 
    AC aC = new AC();
    aC.setConstaints(arrayOfDimConstraint);
    return aC;
  }
  
  private static DimConstraint parseDimConstraint(String paramString, BoundSize paramBoundSize1, BoundSize paramBoundSize2, boolean paramBoolean) { // Byte code:
    //   0: new net/miginfocom/layout/DimConstraint
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore #4
    //   9: aload #4
    //   11: aload_1
    //   12: invokevirtual setGapBefore : (Lnet/miginfocom/layout/BoundSize;)V
    //   15: aload #4
    //   17: aload_2
    //   18: invokevirtual setGapAfter : (Lnet/miginfocom/layout/BoundSize;)V
    //   21: aload_0
    //   22: bipush #44
    //   24: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   27: astore #5
    //   29: iconst_0
    //   30: istore #6
    //   32: iload #6
    //   34: aload #5
    //   36: arraylength
    //   37: if_icmpge -> 516
    //   40: aload #5
    //   42: iload #6
    //   44: aaload
    //   45: astore #7
    //   47: aload #7
    //   49: invokevirtual length : ()I
    //   52: ifne -> 58
    //   55: goto -> 510
    //   58: aload #7
    //   60: ldc 'fill'
    //   62: invokevirtual equals : (Ljava/lang/Object;)Z
    //   65: ifeq -> 77
    //   68: aload #4
    //   70: iconst_1
    //   71: invokevirtual setFill : (Z)V
    //   74: goto -> 510
    //   77: aload #7
    //   79: ldc 'nogrid'
    //   81: invokevirtual equals : (Ljava/lang/Object;)Z
    //   84: ifeq -> 96
    //   87: aload #4
    //   89: iconst_1
    //   90: invokevirtual setNoGrid : (Z)V
    //   93: goto -> 510
    //   96: iconst_m1
    //   97: istore #8
    //   99: aload #7
    //   101: iconst_0
    //   102: invokevirtual charAt : (I)C
    //   105: istore #9
    //   107: iload #9
    //   109: bipush #115
    //   111: if_icmpne -> 274
    //   114: aload #7
    //   116: iconst_2
    //   117: anewarray java/lang/String
    //   120: dup
    //   121: iconst_0
    //   122: ldc 'sizegroup'
    //   124: aastore
    //   125: dup
    //   126: iconst_1
    //   127: ldc 'sg'
    //   129: aastore
    //   130: iconst_2
    //   131: newarray int
    //   133: dup
    //   134: iconst_0
    //   135: iconst_5
    //   136: iastore
    //   137: dup
    //   138: iconst_1
    //   139: iconst_2
    //   140: iastore
    //   141: iconst_1
    //   142: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   145: istore #8
    //   147: iload #8
    //   149: iconst_m1
    //   150: if_icmple -> 171
    //   153: aload #4
    //   155: aload #7
    //   157: iload #8
    //   159: invokevirtual substring : (I)Ljava/lang/String;
    //   162: invokevirtual trim : ()Ljava/lang/String;
    //   165: invokevirtual setSizeGroup : (Ljava/lang/String;)V
    //   168: goto -> 510
    //   171: aload #7
    //   173: iconst_2
    //   174: anewarray java/lang/String
    //   177: dup
    //   178: iconst_0
    //   179: ldc 'shrinkprio'
    //   181: aastore
    //   182: dup
    //   183: iconst_1
    //   184: ldc 'shp'
    //   186: aastore
    //   187: iconst_2
    //   188: newarray int
    //   190: dup
    //   191: iconst_0
    //   192: bipush #10
    //   194: iastore
    //   195: dup
    //   196: iconst_1
    //   197: iconst_3
    //   198: iastore
    //   199: iconst_1
    //   200: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   203: istore #8
    //   205: iload #8
    //   207: iconst_m1
    //   208: if_icmple -> 232
    //   211: aload #4
    //   213: aload #7
    //   215: iload #8
    //   217: invokevirtual substring : (I)Ljava/lang/String;
    //   220: invokevirtual trim : ()Ljava/lang/String;
    //   223: invokestatic parseInt : (Ljava/lang/String;)I
    //   226: invokevirtual setShrinkPriority : (I)V
    //   229: goto -> 510
    //   232: aload #7
    //   234: ldc 'shrink'
    //   236: bipush #6
    //   238: iconst_1
    //   239: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   242: istore #8
    //   244: iload #8
    //   246: iconst_m1
    //   247: if_icmple -> 274
    //   250: aload #4
    //   252: aload #7
    //   254: iload #8
    //   256: invokevirtual substring : (I)Ljava/lang/String;
    //   259: invokevirtual trim : ()Ljava/lang/String;
    //   262: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   265: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   268: invokevirtual setShrink : (Ljava/lang/Float;)V
    //   271: goto -> 510
    //   274: iload #9
    //   276: bipush #103
    //   278: if_icmpne -> 382
    //   281: aload #7
    //   283: iconst_2
    //   284: anewarray java/lang/String
    //   287: dup
    //   288: iconst_0
    //   289: ldc 'growpriority'
    //   291: aastore
    //   292: dup
    //   293: iconst_1
    //   294: ldc 'gp'
    //   296: aastore
    //   297: iconst_2
    //   298: newarray int
    //   300: dup
    //   301: iconst_0
    //   302: iconst_5
    //   303: iastore
    //   304: dup
    //   305: iconst_1
    //   306: iconst_2
    //   307: iastore
    //   308: iconst_1
    //   309: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   312: istore #8
    //   314: iload #8
    //   316: iconst_m1
    //   317: if_icmple -> 341
    //   320: aload #4
    //   322: aload #7
    //   324: iload #8
    //   326: invokevirtual substring : (I)Ljava/lang/String;
    //   329: invokevirtual trim : ()Ljava/lang/String;
    //   332: invokestatic parseInt : (Ljava/lang/String;)I
    //   335: invokevirtual setGrowPriority : (I)V
    //   338: goto -> 510
    //   341: aload #7
    //   343: ldc 'grow'
    //   345: iconst_4
    //   346: iconst_1
    //   347: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   350: istore #8
    //   352: iload #8
    //   354: iconst_m1
    //   355: if_icmple -> 382
    //   358: aload #4
    //   360: aload #7
    //   362: iload #8
    //   364: invokevirtual substring : (I)Ljava/lang/String;
    //   367: invokevirtual trim : ()Ljava/lang/String;
    //   370: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   373: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   376: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   379: goto -> 510
    //   382: iload #9
    //   384: bipush #97
    //   386: if_icmpne -> 429
    //   389: aload #7
    //   391: ldc 'align'
    //   393: iconst_2
    //   394: iconst_1
    //   395: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   398: istore #8
    //   400: iload #8
    //   402: iconst_m1
    //   403: if_icmple -> 429
    //   406: aload #4
    //   408: aload #7
    //   410: iload #8
    //   412: invokevirtual substring : (I)Ljava/lang/String;
    //   415: invokevirtual trim : ()Ljava/lang/String;
    //   418: iload_3
    //   419: aconst_null
    //   420: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   423: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   426: goto -> 510
    //   429: aload #7
    //   431: iload_3
    //   432: invokestatic parseAlignKeywords : (Ljava/lang/String;Z)Lnet/miginfocom/layout/UnitValue;
    //   435: astore #10
    //   437: aload #10
    //   439: ifnull -> 452
    //   442: aload #4
    //   444: aload #10
    //   446: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   449: goto -> 510
    //   452: aload #4
    //   454: aload #7
    //   456: iconst_0
    //   457: iload_3
    //   458: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   461: invokevirtual setSize : (Lnet/miginfocom/layout/BoundSize;)V
    //   464: goto -> 510
    //   467: astore #8
    //   469: new java/lang/IllegalArgumentException
    //   472: dup
    //   473: new java/lang/StringBuilder
    //   476: dup
    //   477: invokespecial <init> : ()V
    //   480: ldc 'Illegal contraint: ''
    //   482: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   485: aload #7
    //   487: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   490: ldc ''\\n'
    //   492: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   495: aload #8
    //   497: invokevirtual getMessage : ()Ljava/lang/String;
    //   500: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   503: invokevirtual toString : ()Ljava/lang/String;
    //   506: invokespecial <init> : (Ljava/lang/String;)V
    //   509: athrow
    //   510: iinc #6, 1
    //   513: goto -> 32
    //   516: aload #4
    //   518: areturn
    // Exception table:
    //   from	to	target	type
    //   47	55	467	java/lang/Exception
    //   58	74	467	java/lang/Exception
    //   77	93	467	java/lang/Exception
    //   96	168	467	java/lang/Exception
    //   171	229	467	java/lang/Exception
    //   232	271	467	java/lang/Exception
    //   274	338	467	java/lang/Exception
    //   341	379	467	java/lang/Exception
    //   382	426	467	java/lang/Exception
    //   429	449	467	java/lang/Exception
    //   452	464	467	java/lang/Exception }
  
  public static Map<ComponentWrapper, CC> parseComponentConstraints(Map<ComponentWrapper, String> paramMap) {
    HashMap hashMap = new HashMap();
    for (Map.Entry entry : paramMap.entrySet())
      hashMap.put(entry.getKey(), parseComponentConstraint((String)entry.getValue())); 
    return hashMap;
  }
  
  public static CC parseComponentConstraint(String paramString) { // Byte code:
    //   0: new net/miginfocom/layout/CC
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aload_0
    //   9: invokevirtual length : ()I
    //   12: ifne -> 17
    //   15: aload_1
    //   16: areturn
    //   17: aload_0
    //   18: bipush #44
    //   20: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   23: astore_2
    //   24: iconst_0
    //   25: istore_3
    //   26: iload_3
    //   27: aload_2
    //   28: arraylength
    //   29: if_icmpge -> 3315
    //   32: aload_2
    //   33: iload_3
    //   34: aaload
    //   35: astore #4
    //   37: aload #4
    //   39: invokevirtual length : ()I
    //   42: ifne -> 48
    //   45: goto -> 3309
    //   48: iconst_m1
    //   49: istore #5
    //   51: aload #4
    //   53: iconst_0
    //   54: invokevirtual charAt : (I)C
    //   57: istore #6
    //   59: iload #6
    //   61: bipush #110
    //   63: if_icmpne -> 138
    //   66: aload #4
    //   68: ldc 'north'
    //   70: invokevirtual equals : (Ljava/lang/Object;)Z
    //   73: ifeq -> 84
    //   76: aload_1
    //   77: iconst_0
    //   78: invokevirtual setDockSide : (I)V
    //   81: goto -> 3309
    //   84: aload #4
    //   86: ldc 'newline'
    //   88: invokevirtual equals : (Ljava/lang/Object;)Z
    //   91: ifeq -> 102
    //   94: aload_1
    //   95: iconst_1
    //   96: invokevirtual setNewline : (Z)V
    //   99: goto -> 3309
    //   102: aload #4
    //   104: ldc 'newline '
    //   106: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   109: ifeq -> 138
    //   112: aload #4
    //   114: bipush #7
    //   116: invokevirtual substring : (I)Ljava/lang/String;
    //   119: invokevirtual trim : ()Ljava/lang/String;
    //   122: astore #7
    //   124: aload_1
    //   125: aload #7
    //   127: iconst_1
    //   128: iconst_1
    //   129: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   132: invokevirtual setNewlineGapSize : (Lnet/miginfocom/layout/BoundSize;)V
    //   135: goto -> 3309
    //   138: iload #6
    //   140: bipush #102
    //   142: if_icmpne -> 192
    //   145: aload #4
    //   147: ldc 'flowy'
    //   149: invokevirtual equals : (Ljava/lang/Object;)Z
    //   152: ifne -> 165
    //   155: aload #4
    //   157: ldc 'flowx'
    //   159: invokevirtual equals : (Ljava/lang/Object;)Z
    //   162: ifeq -> 192
    //   165: aload_1
    //   166: aload #4
    //   168: iconst_4
    //   169: invokevirtual charAt : (I)C
    //   172: bipush #120
    //   174: if_icmpne -> 183
    //   177: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
    //   180: goto -> 186
    //   183: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
    //   186: invokevirtual setFlowX : (Ljava/lang/Boolean;)V
    //   189: goto -> 3309
    //   192: iload #6
    //   194: bipush #115
    //   196: if_icmpne -> 978
    //   199: aload #4
    //   201: ldc 'skip'
    //   203: iconst_4
    //   204: iconst_1
    //   205: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   208: istore #5
    //   210: iload #5
    //   212: iconst_m1
    //   213: if_icmple -> 252
    //   216: aload #4
    //   218: iload #5
    //   220: invokevirtual substring : (I)Ljava/lang/String;
    //   223: invokevirtual trim : ()Ljava/lang/String;
    //   226: astore #7
    //   228: aload_1
    //   229: aload #7
    //   231: invokevirtual length : ()I
    //   234: ifeq -> 245
    //   237: aload #7
    //   239: invokestatic parseInt : (Ljava/lang/String;)I
    //   242: goto -> 246
    //   245: iconst_1
    //   246: invokevirtual setSkip : (I)V
    //   249: goto -> 3309
    //   252: aload #4
    //   254: ldc 'split'
    //   256: iconst_5
    //   257: iconst_1
    //   258: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   261: istore #5
    //   263: iload #5
    //   265: iconst_m1
    //   266: if_icmple -> 306
    //   269: aload #4
    //   271: iload #5
    //   273: invokevirtual substring : (I)Ljava/lang/String;
    //   276: invokevirtual trim : ()Ljava/lang/String;
    //   279: astore #7
    //   281: aload_1
    //   282: aload #7
    //   284: invokevirtual length : ()I
    //   287: ifle -> 298
    //   290: aload #7
    //   292: invokestatic parseInt : (Ljava/lang/String;)I
    //   295: goto -> 300
    //   298: ldc 2097051
    //   300: invokevirtual setSplit : (I)V
    //   303: goto -> 3309
    //   306: aload #4
    //   308: ldc 'south'
    //   310: invokevirtual equals : (Ljava/lang/Object;)Z
    //   313: ifeq -> 324
    //   316: aload_1
    //   317: iconst_2
    //   318: invokevirtual setDockSide : (I)V
    //   321: goto -> 3309
    //   324: aload #4
    //   326: iconst_2
    //   327: anewarray java/lang/String
    //   330: dup
    //   331: iconst_0
    //   332: ldc 'spany'
    //   334: aastore
    //   335: dup
    //   336: iconst_1
    //   337: ldc 'sy'
    //   339: aastore
    //   340: iconst_2
    //   341: newarray int
    //   343: dup
    //   344: iconst_0
    //   345: iconst_5
    //   346: iastore
    //   347: dup
    //   348: iconst_1
    //   349: iconst_2
    //   350: iastore
    //   351: iconst_1
    //   352: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   355: istore #5
    //   357: iload #5
    //   359: iconst_m1
    //   360: if_icmple -> 383
    //   363: aload_1
    //   364: aload #4
    //   366: iload #5
    //   368: invokevirtual substring : (I)Ljava/lang/String;
    //   371: invokevirtual trim : ()Ljava/lang/String;
    //   374: invokestatic parseSpan : (Ljava/lang/String;)I
    //   377: invokevirtual setSpanY : (I)V
    //   380: goto -> 3309
    //   383: aload #4
    //   385: iconst_2
    //   386: anewarray java/lang/String
    //   389: dup
    //   390: iconst_0
    //   391: ldc 'spanx'
    //   393: aastore
    //   394: dup
    //   395: iconst_1
    //   396: ldc 'sx'
    //   398: aastore
    //   399: iconst_2
    //   400: newarray int
    //   402: dup
    //   403: iconst_0
    //   404: iconst_5
    //   405: iastore
    //   406: dup
    //   407: iconst_1
    //   408: iconst_2
    //   409: iastore
    //   410: iconst_1
    //   411: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   414: istore #5
    //   416: iload #5
    //   418: iconst_m1
    //   419: if_icmple -> 442
    //   422: aload_1
    //   423: aload #4
    //   425: iload #5
    //   427: invokevirtual substring : (I)Ljava/lang/String;
    //   430: invokevirtual trim : ()Ljava/lang/String;
    //   433: invokestatic parseSpan : (Ljava/lang/String;)I
    //   436: invokevirtual setSpanX : (I)V
    //   439: goto -> 3309
    //   442: aload #4
    //   444: ldc 'span'
    //   446: iconst_4
    //   447: iconst_1
    //   448: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   451: istore #5
    //   453: iload #5
    //   455: iconst_m1
    //   456: if_icmple -> 527
    //   459: aload #4
    //   461: iload #5
    //   463: invokevirtual substring : (I)Ljava/lang/String;
    //   466: invokevirtual trim : ()Ljava/lang/String;
    //   469: bipush #32
    //   471: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   474: astore #7
    //   476: aload_1
    //   477: aload #7
    //   479: iconst_0
    //   480: aaload
    //   481: invokevirtual length : ()I
    //   484: ifle -> 497
    //   487: aload #7
    //   489: iconst_0
    //   490: aaload
    //   491: invokestatic parseInt : (Ljava/lang/String;)I
    //   494: goto -> 499
    //   497: ldc 2097051
    //   499: invokevirtual setSpanX : (I)V
    //   502: aload_1
    //   503: aload #7
    //   505: arraylength
    //   506: iconst_1
    //   507: if_icmple -> 520
    //   510: aload #7
    //   512: iconst_1
    //   513: aaload
    //   514: invokestatic parseInt : (Ljava/lang/String;)I
    //   517: goto -> 521
    //   520: iconst_1
    //   521: invokevirtual setSpanY : (I)V
    //   524: goto -> 3309
    //   527: aload #4
    //   529: ldc 'shrinkx'
    //   531: bipush #7
    //   533: iconst_1
    //   534: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   537: istore #5
    //   539: iload #5
    //   541: iconst_m1
    //   542: if_icmple -> 571
    //   545: aload_1
    //   546: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   549: aload #4
    //   551: iload #5
    //   553: invokevirtual substring : (I)Ljava/lang/String;
    //   556: invokevirtual trim : ()Ljava/lang/String;
    //   559: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   562: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   565: invokevirtual setShrink : (Ljava/lang/Float;)V
    //   568: goto -> 3309
    //   571: aload #4
    //   573: ldc 'shrinky'
    //   575: bipush #7
    //   577: iconst_1
    //   578: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   581: istore #5
    //   583: iload #5
    //   585: iconst_m1
    //   586: if_icmple -> 615
    //   589: aload_1
    //   590: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   593: aload #4
    //   595: iload #5
    //   597: invokevirtual substring : (I)Ljava/lang/String;
    //   600: invokevirtual trim : ()Ljava/lang/String;
    //   603: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   606: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   609: invokevirtual setShrink : (Ljava/lang/Float;)V
    //   612: goto -> 3309
    //   615: aload #4
    //   617: ldc 'shrink'
    //   619: bipush #6
    //   621: iconst_0
    //   622: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   625: istore #5
    //   627: iload #5
    //   629: iconst_m1
    //   630: if_icmple -> 706
    //   633: aload #4
    //   635: iload #5
    //   637: invokevirtual substring : (I)Ljava/lang/String;
    //   640: invokevirtual trim : ()Ljava/lang/String;
    //   643: bipush #32
    //   645: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   648: astore #7
    //   650: aload_1
    //   651: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   654: aload #4
    //   656: iload #5
    //   658: invokevirtual substring : (I)Ljava/lang/String;
    //   661: invokevirtual trim : ()Ljava/lang/String;
    //   664: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   667: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   670: invokevirtual setShrink : (Ljava/lang/Float;)V
    //   673: aload #7
    //   675: arraylength
    //   676: iconst_1
    //   677: if_icmple -> 703
    //   680: aload_1
    //   681: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   684: aload #4
    //   686: iload #5
    //   688: invokevirtual substring : (I)Ljava/lang/String;
    //   691: invokevirtual trim : ()Ljava/lang/String;
    //   694: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   697: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   700: invokevirtual setShrink : (Ljava/lang/Float;)V
    //   703: goto -> 3309
    //   706: aload #4
    //   708: iconst_2
    //   709: anewarray java/lang/String
    //   712: dup
    //   713: iconst_0
    //   714: ldc 'shrinkprio'
    //   716: aastore
    //   717: dup
    //   718: iconst_1
    //   719: ldc 'shp'
    //   721: aastore
    //   722: iconst_2
    //   723: newarray int
    //   725: dup
    //   726: iconst_0
    //   727: bipush #10
    //   729: iastore
    //   730: dup
    //   731: iconst_1
    //   732: iconst_3
    //   733: iastore
    //   734: iconst_1
    //   735: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   738: istore #5
    //   740: iload #5
    //   742: iconst_m1
    //   743: if_icmple -> 861
    //   746: aload #4
    //   748: iload #5
    //   750: invokevirtual substring : (I)Ljava/lang/String;
    //   753: invokevirtual trim : ()Ljava/lang/String;
    //   756: astore #7
    //   758: aload #7
    //   760: ldc 'x'
    //   762: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   765: ifne -> 778
    //   768: aload #7
    //   770: ldc 'y'
    //   772: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   775: ifeq -> 814
    //   778: aload #7
    //   780: ldc 'x'
    //   782: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   785: ifeq -> 795
    //   788: aload_1
    //   789: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   792: goto -> 799
    //   795: aload_1
    //   796: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   799: aload #7
    //   801: iconst_2
    //   802: invokevirtual substring : (I)Ljava/lang/String;
    //   805: invokestatic parseInt : (Ljava/lang/String;)I
    //   808: invokevirtual setShrinkPriority : (I)V
    //   811: goto -> 858
    //   814: aload #7
    //   816: bipush #32
    //   818: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   821: astore #8
    //   823: aload_1
    //   824: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   827: aload #8
    //   829: iconst_0
    //   830: aaload
    //   831: invokestatic parseInt : (Ljava/lang/String;)I
    //   834: invokevirtual setShrinkPriority : (I)V
    //   837: aload #8
    //   839: arraylength
    //   840: iconst_1
    //   841: if_icmple -> 858
    //   844: aload_1
    //   845: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   848: aload #8
    //   850: iconst_1
    //   851: aaload
    //   852: invokestatic parseInt : (Ljava/lang/String;)I
    //   855: invokevirtual setShrinkPriority : (I)V
    //   858: goto -> 3309
    //   861: aload #4
    //   863: iconst_4
    //   864: anewarray java/lang/String
    //   867: dup
    //   868: iconst_0
    //   869: ldc 'sizegroupx'
    //   871: aastore
    //   872: dup
    //   873: iconst_1
    //   874: ldc 'sizegroupy'
    //   876: aastore
    //   877: dup
    //   878: iconst_2
    //   879: ldc 'sgx'
    //   881: aastore
    //   882: dup
    //   883: iconst_3
    //   884: ldc 'sgy'
    //   886: aastore
    //   887: iconst_4
    //   888: newarray int
    //   890: dup
    //   891: iconst_0
    //   892: bipush #9
    //   894: iastore
    //   895: dup
    //   896: iconst_1
    //   897: bipush #9
    //   899: iastore
    //   900: dup
    //   901: iconst_2
    //   902: iconst_2
    //   903: iastore
    //   904: dup
    //   905: iconst_3
    //   906: iconst_2
    //   907: iastore
    //   908: iconst_1
    //   909: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   912: istore #5
    //   914: iload #5
    //   916: iconst_m1
    //   917: if_icmple -> 978
    //   920: aload #4
    //   922: iload #5
    //   924: invokevirtual substring : (I)Ljava/lang/String;
    //   927: invokevirtual trim : ()Ljava/lang/String;
    //   930: astore #7
    //   932: aload #4
    //   934: iload #5
    //   936: iconst_1
    //   937: isub
    //   938: invokevirtual charAt : (I)C
    //   941: istore #8
    //   943: iload #8
    //   945: bipush #121
    //   947: if_icmpeq -> 959
    //   950: aload_1
    //   951: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   954: aload #7
    //   956: invokevirtual setSizeGroup : (Ljava/lang/String;)V
    //   959: iload #8
    //   961: bipush #120
    //   963: if_icmpeq -> 975
    //   966: aload_1
    //   967: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   970: aload #7
    //   972: invokevirtual setSizeGroup : (Ljava/lang/String;)V
    //   975: goto -> 3309
    //   978: iload #6
    //   980: bipush #103
    //   982: if_icmpne -> 1413
    //   985: aload #4
    //   987: ldc 'growx'
    //   989: iconst_5
    //   990: iconst_1
    //   991: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   994: istore #5
    //   996: iload #5
    //   998: iconst_m1
    //   999: if_icmple -> 1028
    //   1002: aload_1
    //   1003: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1006: aload #4
    //   1008: iload #5
    //   1010: invokevirtual substring : (I)Ljava/lang/String;
    //   1013: invokevirtual trim : ()Ljava/lang/String;
    //   1016: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   1019: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   1022: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   1025: goto -> 3309
    //   1028: aload #4
    //   1030: ldc 'growy'
    //   1032: iconst_5
    //   1033: iconst_1
    //   1034: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   1037: istore #5
    //   1039: iload #5
    //   1041: iconst_m1
    //   1042: if_icmple -> 1071
    //   1045: aload_1
    //   1046: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1049: aload #4
    //   1051: iload #5
    //   1053: invokevirtual substring : (I)Ljava/lang/String;
    //   1056: invokevirtual trim : ()Ljava/lang/String;
    //   1059: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   1062: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   1065: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   1068: goto -> 3309
    //   1071: aload #4
    //   1073: ldc 'grow'
    //   1075: iconst_4
    //   1076: iconst_0
    //   1077: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   1080: istore #5
    //   1082: iload #5
    //   1084: iconst_m1
    //   1085: if_icmple -> 1154
    //   1088: aload #4
    //   1090: iload #5
    //   1092: invokevirtual substring : (I)Ljava/lang/String;
    //   1095: invokevirtual trim : ()Ljava/lang/String;
    //   1098: bipush #32
    //   1100: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1103: astore #7
    //   1105: aload_1
    //   1106: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1109: aload #7
    //   1111: iconst_0
    //   1112: aaload
    //   1113: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   1116: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   1119: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   1122: aload_1
    //   1123: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1126: aload #7
    //   1128: arraylength
    //   1129: iconst_1
    //   1130: if_icmple -> 1140
    //   1133: aload #7
    //   1135: iconst_1
    //   1136: aaload
    //   1137: goto -> 1142
    //   1140: ldc ''
    //   1142: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   1145: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   1148: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   1151: goto -> 3309
    //   1154: aload #4
    //   1156: iconst_2
    //   1157: anewarray java/lang/String
    //   1160: dup
    //   1161: iconst_0
    //   1162: ldc 'growprio'
    //   1164: aastore
    //   1165: dup
    //   1166: iconst_1
    //   1167: ldc 'gp'
    //   1169: aastore
    //   1170: iconst_2
    //   1171: newarray int
    //   1173: dup
    //   1174: iconst_0
    //   1175: bipush #8
    //   1177: iastore
    //   1178: dup
    //   1179: iconst_1
    //   1180: iconst_2
    //   1181: iastore
    //   1182: iconst_1
    //   1183: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   1186: istore #5
    //   1188: iload #5
    //   1190: iconst_m1
    //   1191: if_icmple -> 1321
    //   1194: aload #4
    //   1196: iload #5
    //   1198: invokevirtual substring : (I)Ljava/lang/String;
    //   1201: invokevirtual trim : ()Ljava/lang/String;
    //   1204: astore #7
    //   1206: aload #7
    //   1208: invokevirtual length : ()I
    //   1211: ifle -> 1223
    //   1214: aload #7
    //   1216: iconst_0
    //   1217: invokevirtual charAt : (I)C
    //   1220: goto -> 1225
    //   1223: bipush #32
    //   1225: istore #8
    //   1227: iload #8
    //   1229: bipush #120
    //   1231: if_icmpeq -> 1241
    //   1234: iload #8
    //   1236: bipush #121
    //   1238: if_icmpne -> 1274
    //   1241: iload #8
    //   1243: bipush #120
    //   1245: if_icmpne -> 1255
    //   1248: aload_1
    //   1249: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1252: goto -> 1259
    //   1255: aload_1
    //   1256: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1259: aload #7
    //   1261: iconst_2
    //   1262: invokevirtual substring : (I)Ljava/lang/String;
    //   1265: invokestatic parseInt : (Ljava/lang/String;)I
    //   1268: invokevirtual setGrowPriority : (I)V
    //   1271: goto -> 1318
    //   1274: aload #7
    //   1276: bipush #32
    //   1278: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1281: astore #9
    //   1283: aload_1
    //   1284: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1287: aload #9
    //   1289: iconst_0
    //   1290: aaload
    //   1291: invokestatic parseInt : (Ljava/lang/String;)I
    //   1294: invokevirtual setGrowPriority : (I)V
    //   1297: aload #9
    //   1299: arraylength
    //   1300: iconst_1
    //   1301: if_icmple -> 1318
    //   1304: aload_1
    //   1305: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1308: aload #9
    //   1310: iconst_1
    //   1311: aaload
    //   1312: invokestatic parseInt : (Ljava/lang/String;)I
    //   1315: invokevirtual setGrowPriority : (I)V
    //   1318: goto -> 3309
    //   1321: aload #4
    //   1323: ldc 'gap'
    //   1325: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1328: ifeq -> 1413
    //   1331: aload #4
    //   1333: invokestatic parseGaps : (Ljava/lang/String;)[Lnet/miginfocom/layout/BoundSize;
    //   1336: astore #7
    //   1338: aload #7
    //   1340: iconst_0
    //   1341: aaload
    //   1342: ifnull -> 1356
    //   1345: aload_1
    //   1346: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1349: aload #7
    //   1351: iconst_0
    //   1352: aaload
    //   1353: invokevirtual setGapBefore : (Lnet/miginfocom/layout/BoundSize;)V
    //   1356: aload #7
    //   1358: iconst_1
    //   1359: aaload
    //   1360: ifnull -> 1374
    //   1363: aload_1
    //   1364: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1367: aload #7
    //   1369: iconst_1
    //   1370: aaload
    //   1371: invokevirtual setGapBefore : (Lnet/miginfocom/layout/BoundSize;)V
    //   1374: aload #7
    //   1376: iconst_2
    //   1377: aaload
    //   1378: ifnull -> 1392
    //   1381: aload_1
    //   1382: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1385: aload #7
    //   1387: iconst_2
    //   1388: aaload
    //   1389: invokevirtual setGapAfter : (Lnet/miginfocom/layout/BoundSize;)V
    //   1392: aload #7
    //   1394: iconst_3
    //   1395: aaload
    //   1396: ifnull -> 1410
    //   1399: aload_1
    //   1400: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1403: aload #7
    //   1405: iconst_3
    //   1406: aaload
    //   1407: invokevirtual setGapAfter : (Lnet/miginfocom/layout/BoundSize;)V
    //   1410: goto -> 3309
    //   1413: iload #6
    //   1415: bipush #97
    //   1417: if_icmpne -> 1626
    //   1420: aload #4
    //   1422: iconst_2
    //   1423: anewarray java/lang/String
    //   1426: dup
    //   1427: iconst_0
    //   1428: ldc 'aligny'
    //   1430: aastore
    //   1431: dup
    //   1432: iconst_1
    //   1433: ldc 'ay'
    //   1435: aastore
    //   1436: iconst_2
    //   1437: newarray int
    //   1439: dup
    //   1440: iconst_0
    //   1441: bipush #6
    //   1443: iastore
    //   1444: dup
    //   1445: iconst_1
    //   1446: iconst_2
    //   1447: iastore
    //   1448: iconst_1
    //   1449: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   1452: istore #5
    //   1454: iload #5
    //   1456: iconst_m1
    //   1457: if_icmple -> 1485
    //   1460: aload_1
    //   1461: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1464: aload #4
    //   1466: iload #5
    //   1468: invokevirtual substring : (I)Ljava/lang/String;
    //   1471: invokevirtual trim : ()Ljava/lang/String;
    //   1474: iconst_0
    //   1475: aconst_null
    //   1476: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1479: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   1482: goto -> 3309
    //   1485: aload #4
    //   1487: iconst_2
    //   1488: anewarray java/lang/String
    //   1491: dup
    //   1492: iconst_0
    //   1493: ldc 'alignx'
    //   1495: aastore
    //   1496: dup
    //   1497: iconst_1
    //   1498: ldc 'ax'
    //   1500: aastore
    //   1501: iconst_2
    //   1502: newarray int
    //   1504: dup
    //   1505: iconst_0
    //   1506: bipush #6
    //   1508: iastore
    //   1509: dup
    //   1510: iconst_1
    //   1511: iconst_2
    //   1512: iastore
    //   1513: iconst_1
    //   1514: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   1517: istore #5
    //   1519: iload #5
    //   1521: iconst_m1
    //   1522: if_icmple -> 1550
    //   1525: aload_1
    //   1526: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1529: aload #4
    //   1531: iload #5
    //   1533: invokevirtual substring : (I)Ljava/lang/String;
    //   1536: invokevirtual trim : ()Ljava/lang/String;
    //   1539: iconst_1
    //   1540: aconst_null
    //   1541: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1544: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   1547: goto -> 3309
    //   1550: aload #4
    //   1552: ldc 'align'
    //   1554: iconst_2
    //   1555: iconst_1
    //   1556: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   1559: istore #5
    //   1561: iload #5
    //   1563: iconst_m1
    //   1564: if_icmple -> 1626
    //   1567: aload #4
    //   1569: iload #5
    //   1571: invokevirtual substring : (I)Ljava/lang/String;
    //   1574: invokevirtual trim : ()Ljava/lang/String;
    //   1577: bipush #32
    //   1579: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1582: astore #7
    //   1584: aload_1
    //   1585: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   1588: aload #7
    //   1590: iconst_0
    //   1591: aaload
    //   1592: iconst_1
    //   1593: aconst_null
    //   1594: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1597: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   1600: aload #7
    //   1602: arraylength
    //   1603: iconst_1
    //   1604: if_icmple -> 1623
    //   1607: aload_1
    //   1608: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   1611: aload #7
    //   1613: iconst_1
    //   1614: aaload
    //   1615: iconst_0
    //   1616: aconst_null
    //   1617: invokestatic parseUnitValueOrAlign : (Ljava/lang/String;ZLnet/miginfocom/layout/UnitValue;)Lnet/miginfocom/layout/UnitValue;
    //   1620: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   1623: goto -> 3309
    //   1626: iload #6
    //   1628: bipush #120
    //   1630: if_icmpeq -> 1640
    //   1633: iload #6
    //   1635: bipush #121
    //   1637: if_icmpne -> 1794
    //   1640: aload #4
    //   1642: invokevirtual length : ()I
    //   1645: iconst_2
    //   1646: if_icmple -> 1794
    //   1649: aload #4
    //   1651: iconst_1
    //   1652: invokevirtual charAt : (I)C
    //   1655: istore #7
    //   1657: iload #7
    //   1659: bipush #32
    //   1661: if_icmpeq -> 1682
    //   1664: iload #7
    //   1666: bipush #50
    //   1668: if_icmpne -> 1794
    //   1671: aload #4
    //   1673: iconst_2
    //   1674: invokevirtual charAt : (I)C
    //   1677: bipush #32
    //   1679: if_icmpne -> 1794
    //   1682: aload_1
    //   1683: invokevirtual getPos : ()[Lnet/miginfocom/layout/UnitValue;
    //   1686: ifnonnull -> 1700
    //   1689: aload_1
    //   1690: iconst_4
    //   1691: anewarray net/miginfocom/layout/UnitValue
    //   1694: invokevirtual setPos : ([Lnet/miginfocom/layout/UnitValue;)V
    //   1697: goto -> 1717
    //   1700: aload_1
    //   1701: invokevirtual isBoundsInGrid : ()Z
    //   1704: ifne -> 1717
    //   1707: new java/lang/IllegalArgumentException
    //   1710: dup
    //   1711: ldc 'Cannot combine 'position' with 'x/y/x2/y2' keywords.'
    //   1713: invokespecial <init> : (Ljava/lang/String;)V
    //   1716: athrow
    //   1717: iload #6
    //   1719: bipush #120
    //   1721: if_icmpne -> 1728
    //   1724: iconst_0
    //   1725: goto -> 1729
    //   1728: iconst_1
    //   1729: iload #7
    //   1731: bipush #50
    //   1733: if_icmpne -> 1740
    //   1736: iconst_2
    //   1737: goto -> 1741
    //   1740: iconst_0
    //   1741: iadd
    //   1742: istore #8
    //   1744: aload_1
    //   1745: invokevirtual getPos : ()[Lnet/miginfocom/layout/UnitValue;
    //   1748: astore #9
    //   1750: aload #9
    //   1752: iload #8
    //   1754: aload #4
    //   1756: iconst_2
    //   1757: invokevirtual substring : (I)Ljava/lang/String;
    //   1760: invokevirtual trim : ()Ljava/lang/String;
    //   1763: aconst_null
    //   1764: iload #6
    //   1766: bipush #120
    //   1768: if_icmpne -> 1775
    //   1771: iconst_1
    //   1772: goto -> 1776
    //   1775: iconst_0
    //   1776: invokestatic parseUnitValue : (Ljava/lang/String;Lnet/miginfocom/layout/UnitValue;Z)Lnet/miginfocom/layout/UnitValue;
    //   1779: aastore
    //   1780: aload_1
    //   1781: aload #9
    //   1783: invokevirtual setPos : ([Lnet/miginfocom/layout/UnitValue;)V
    //   1786: aload_1
    //   1787: iconst_1
    //   1788: invokevirtual setBoundsInGrid : (Z)V
    //   1791: goto -> 3309
    //   1794: iload #6
    //   1796: bipush #99
    //   1798: if_icmpne -> 1931
    //   1801: aload #4
    //   1803: ldc 'cell'
    //   1805: iconst_4
    //   1806: iconst_1
    //   1807: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   1810: istore #5
    //   1812: iload #5
    //   1814: iconst_m1
    //   1815: if_icmple -> 1931
    //   1818: aload #4
    //   1820: iload #5
    //   1822: invokevirtual substring : (I)Ljava/lang/String;
    //   1825: invokevirtual trim : ()Ljava/lang/String;
    //   1828: bipush #32
    //   1830: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1833: astore #7
    //   1835: aload #7
    //   1837: arraylength
    //   1838: iconst_2
    //   1839: if_icmpge -> 1870
    //   1842: new java/lang/IllegalArgumentException
    //   1845: dup
    //   1846: new java/lang/StringBuilder
    //   1849: dup
    //   1850: invokespecial <init> : ()V
    //   1853: ldc 'At least two integers must follow '
    //   1855: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1858: aload #4
    //   1860: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1863: invokevirtual toString : ()Ljava/lang/String;
    //   1866: invokespecial <init> : (Ljava/lang/String;)V
    //   1869: athrow
    //   1870: aload_1
    //   1871: aload #7
    //   1873: iconst_0
    //   1874: aaload
    //   1875: invokestatic parseInt : (Ljava/lang/String;)I
    //   1878: invokevirtual setCellX : (I)V
    //   1881: aload_1
    //   1882: aload #7
    //   1884: iconst_1
    //   1885: aaload
    //   1886: invokestatic parseInt : (Ljava/lang/String;)I
    //   1889: invokevirtual setCellY : (I)V
    //   1892: aload #7
    //   1894: arraylength
    //   1895: iconst_2
    //   1896: if_icmple -> 1910
    //   1899: aload_1
    //   1900: aload #7
    //   1902: iconst_2
    //   1903: aaload
    //   1904: invokestatic parseInt : (Ljava/lang/String;)I
    //   1907: invokevirtual setSpanX : (I)V
    //   1910: aload #7
    //   1912: arraylength
    //   1913: iconst_3
    //   1914: if_icmple -> 1928
    //   1917: aload_1
    //   1918: aload #7
    //   1920: iconst_3
    //   1921: aaload
    //   1922: invokestatic parseInt : (Ljava/lang/String;)I
    //   1925: invokevirtual setSpanY : (I)V
    //   1928: goto -> 3309
    //   1931: iload #6
    //   1933: bipush #112
    //   1935: if_icmpne -> 2359
    //   1938: aload #4
    //   1940: ldc 'pos'
    //   1942: iconst_3
    //   1943: iconst_1
    //   1944: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   1947: istore #5
    //   1949: iload #5
    //   1951: iconst_m1
    //   1952: if_icmple -> 2097
    //   1955: aload_1
    //   1956: invokevirtual getPos : ()[Lnet/miginfocom/layout/UnitValue;
    //   1959: ifnull -> 1979
    //   1962: aload_1
    //   1963: invokevirtual isBoundsInGrid : ()Z
    //   1966: ifeq -> 1979
    //   1969: new java/lang/IllegalArgumentException
    //   1972: dup
    //   1973: ldc 'Can not combine 'pos' with 'x/y/x2/y2' keywords.'
    //   1975: invokespecial <init> : (Ljava/lang/String;)V
    //   1978: athrow
    //   1979: aload #4
    //   1981: iload #5
    //   1983: invokevirtual substring : (I)Ljava/lang/String;
    //   1986: invokevirtual trim : ()Ljava/lang/String;
    //   1989: bipush #32
    //   1991: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   1994: astore #7
    //   1996: iconst_4
    //   1997: anewarray net/miginfocom/layout/UnitValue
    //   2000: astore #8
    //   2002: iconst_0
    //   2003: istore #9
    //   2005: iload #9
    //   2007: aload #7
    //   2009: arraylength
    //   2010: if_icmpge -> 2045
    //   2013: aload #8
    //   2015: iload #9
    //   2017: aload #7
    //   2019: iload #9
    //   2021: aaload
    //   2022: aconst_null
    //   2023: iload #9
    //   2025: iconst_2
    //   2026: irem
    //   2027: ifne -> 2034
    //   2030: iconst_1
    //   2031: goto -> 2035
    //   2034: iconst_0
    //   2035: invokestatic parseUnitValue : (Ljava/lang/String;Lnet/miginfocom/layout/UnitValue;Z)Lnet/miginfocom/layout/UnitValue;
    //   2038: aastore
    //   2039: iinc #9, 1
    //   2042: goto -> 2005
    //   2045: aload #8
    //   2047: iconst_0
    //   2048: aaload
    //   2049: ifnonnull -> 2059
    //   2052: aload #8
    //   2054: iconst_2
    //   2055: aaload
    //   2056: ifnull -> 2073
    //   2059: aload #8
    //   2061: iconst_1
    //   2062: aaload
    //   2063: ifnonnull -> 2083
    //   2066: aload #8
    //   2068: iconst_3
    //   2069: aaload
    //   2070: ifnonnull -> 2083
    //   2073: new java/lang/IllegalArgumentException
    //   2076: dup
    //   2077: ldc 'Both x and x2 or y and y2 can not be null!'
    //   2079: invokespecial <init> : (Ljava/lang/String;)V
    //   2082: athrow
    //   2083: aload_1
    //   2084: aload #8
    //   2086: invokevirtual setPos : ([Lnet/miginfocom/layout/UnitValue;)V
    //   2089: aload_1
    //   2090: iconst_0
    //   2091: invokevirtual setBoundsInGrid : (Z)V
    //   2094: goto -> 3309
    //   2097: aload #4
    //   2099: ldc 'pad'
    //   2101: iconst_3
    //   2102: iconst_1
    //   2103: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   2106: istore #5
    //   2108: iload #5
    //   2110: iconst_m1
    //   2111: if_icmple -> 2202
    //   2114: aload #4
    //   2116: iload #5
    //   2118: invokevirtual substring : (I)Ljava/lang/String;
    //   2121: invokevirtual trim : ()Ljava/lang/String;
    //   2124: iconst_0
    //   2125: invokestatic parseInsets : (Ljava/lang/String;Z)[Lnet/miginfocom/layout/UnitValue;
    //   2128: astore #7
    //   2130: aload_1
    //   2131: iconst_4
    //   2132: anewarray net/miginfocom/layout/UnitValue
    //   2135: dup
    //   2136: iconst_0
    //   2137: aload #7
    //   2139: iconst_0
    //   2140: aaload
    //   2141: aastore
    //   2142: dup
    //   2143: iconst_1
    //   2144: aload #7
    //   2146: arraylength
    //   2147: iconst_1
    //   2148: if_icmple -> 2158
    //   2151: aload #7
    //   2153: iconst_1
    //   2154: aaload
    //   2155: goto -> 2159
    //   2158: aconst_null
    //   2159: aastore
    //   2160: dup
    //   2161: iconst_2
    //   2162: aload #7
    //   2164: arraylength
    //   2165: iconst_2
    //   2166: if_icmple -> 2176
    //   2169: aload #7
    //   2171: iconst_2
    //   2172: aaload
    //   2173: goto -> 2177
    //   2176: aconst_null
    //   2177: aastore
    //   2178: dup
    //   2179: iconst_3
    //   2180: aload #7
    //   2182: arraylength
    //   2183: iconst_3
    //   2184: if_icmple -> 2194
    //   2187: aload #7
    //   2189: iconst_3
    //   2190: aaload
    //   2191: goto -> 2195
    //   2194: aconst_null
    //   2195: aastore
    //   2196: invokevirtual setPadding : ([Lnet/miginfocom/layout/UnitValue;)V
    //   2199: goto -> 3309
    //   2202: aload #4
    //   2204: ldc 'pushx'
    //   2206: iconst_5
    //   2207: iconst_1
    //   2208: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   2211: istore #5
    //   2213: iload #5
    //   2215: iconst_m1
    //   2216: if_icmple -> 2242
    //   2219: aload_1
    //   2220: aload #4
    //   2222: iload #5
    //   2224: invokevirtual substring : (I)Ljava/lang/String;
    //   2227: invokevirtual trim : ()Ljava/lang/String;
    //   2230: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   2233: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   2236: invokevirtual setPushX : (Ljava/lang/Float;)V
    //   2239: goto -> 3309
    //   2242: aload #4
    //   2244: ldc 'pushy'
    //   2246: iconst_5
    //   2247: iconst_1
    //   2248: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   2251: istore #5
    //   2253: iload #5
    //   2255: iconst_m1
    //   2256: if_icmple -> 2282
    //   2259: aload_1
    //   2260: aload #4
    //   2262: iload #5
    //   2264: invokevirtual substring : (I)Ljava/lang/String;
    //   2267: invokevirtual trim : ()Ljava/lang/String;
    //   2270: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   2273: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   2276: invokevirtual setPushY : (Ljava/lang/Float;)V
    //   2279: goto -> 3309
    //   2282: aload #4
    //   2284: ldc 'push'
    //   2286: iconst_4
    //   2287: iconst_0
    //   2288: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   2291: istore #5
    //   2293: iload #5
    //   2295: iconst_m1
    //   2296: if_icmple -> 2359
    //   2299: aload #4
    //   2301: iload #5
    //   2303: invokevirtual substring : (I)Ljava/lang/String;
    //   2306: invokevirtual trim : ()Ljava/lang/String;
    //   2309: bipush #32
    //   2311: invokestatic toTrimmedTokens : (Ljava/lang/String;C)[Ljava/lang/String;
    //   2314: astore #7
    //   2316: aload_1
    //   2317: aload #7
    //   2319: iconst_0
    //   2320: aaload
    //   2321: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   2324: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   2327: invokevirtual setPushX : (Ljava/lang/Float;)V
    //   2330: aload_1
    //   2331: aload #7
    //   2333: arraylength
    //   2334: iconst_1
    //   2335: if_icmple -> 2345
    //   2338: aload #7
    //   2340: iconst_1
    //   2341: aaload
    //   2342: goto -> 2347
    //   2345: ldc ''
    //   2347: getstatic net/miginfocom/layout/ResizeConstraint.WEIGHT_100 : Ljava/lang/Float;
    //   2350: invokestatic parseFloat : (Ljava/lang/String;Ljava/lang/Float;)Ljava/lang/Float;
    //   2353: invokevirtual setPushY : (Ljava/lang/Float;)V
    //   2356: goto -> 3309
    //   2359: iload #6
    //   2361: bipush #116
    //   2363: if_icmpne -> 2400
    //   2366: aload #4
    //   2368: ldc 'tag'
    //   2370: iconst_3
    //   2371: iconst_1
    //   2372: invokestatic startsWithLenient : (Ljava/lang/String;Ljava/lang/String;IZ)I
    //   2375: istore #5
    //   2377: iload #5
    //   2379: iconst_m1
    //   2380: if_icmple -> 2400
    //   2383: aload_1
    //   2384: aload #4
    //   2386: iload #5
    //   2388: invokevirtual substring : (I)Ljava/lang/String;
    //   2391: invokevirtual trim : ()Ljava/lang/String;
    //   2394: invokevirtual setTag : (Ljava/lang/String;)V
    //   2397: goto -> 3309
    //   2400: iload #6
    //   2402: bipush #119
    //   2404: if_icmpeq -> 2414
    //   2407: iload #6
    //   2409: bipush #104
    //   2411: if_icmpne -> 2830
    //   2414: aload #4
    //   2416: ldc 'wrap'
    //   2418: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2421: ifeq -> 2432
    //   2424: aload_1
    //   2425: iconst_1
    //   2426: invokevirtual setWrap : (Z)V
    //   2429: goto -> 3309
    //   2432: aload #4
    //   2434: ldc 'wrap '
    //   2436: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2439: ifeq -> 2467
    //   2442: aload #4
    //   2444: iconst_5
    //   2445: invokevirtual substring : (I)Ljava/lang/String;
    //   2448: invokevirtual trim : ()Ljava/lang/String;
    //   2451: astore #7
    //   2453: aload_1
    //   2454: aload #7
    //   2456: iconst_1
    //   2457: iconst_1
    //   2458: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   2461: invokevirtual setWrapGapSize : (Lnet/miginfocom/layout/BoundSize;)V
    //   2464: goto -> 3309
    //   2467: iload #6
    //   2469: bipush #119
    //   2471: if_icmpne -> 2478
    //   2474: iconst_1
    //   2475: goto -> 2479
    //   2478: iconst_0
    //   2479: istore #7
    //   2481: iload #7
    //   2483: ifeq -> 2550
    //   2486: aload #4
    //   2488: ldc 'w '
    //   2490: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2493: ifne -> 2506
    //   2496: aload #4
    //   2498: ldc 'width '
    //   2500: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2503: ifeq -> 2550
    //   2506: aload #4
    //   2508: aload #4
    //   2510: iconst_1
    //   2511: invokevirtual charAt : (I)C
    //   2514: bipush #32
    //   2516: if_icmpne -> 2523
    //   2519: iconst_2
    //   2520: goto -> 2525
    //   2523: bipush #6
    //   2525: invokevirtual substring : (I)Ljava/lang/String;
    //   2528: invokevirtual trim : ()Ljava/lang/String;
    //   2531: astore #8
    //   2533: aload_1
    //   2534: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   2537: aload #8
    //   2539: iconst_0
    //   2540: iconst_1
    //   2541: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   2544: invokevirtual setSize : (Lnet/miginfocom/layout/BoundSize;)V
    //   2547: goto -> 3309
    //   2550: iload #7
    //   2552: ifne -> 2619
    //   2555: aload #4
    //   2557: ldc 'h '
    //   2559: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2562: ifne -> 2575
    //   2565: aload #4
    //   2567: ldc 'height '
    //   2569: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2572: ifeq -> 2619
    //   2575: aload #4
    //   2577: aload #4
    //   2579: iconst_1
    //   2580: invokevirtual charAt : (I)C
    //   2583: bipush #32
    //   2585: if_icmpne -> 2592
    //   2588: iconst_2
    //   2589: goto -> 2594
    //   2592: bipush #7
    //   2594: invokevirtual substring : (I)Ljava/lang/String;
    //   2597: invokevirtual trim : ()Ljava/lang/String;
    //   2600: astore #8
    //   2602: aload_1
    //   2603: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   2606: aload #8
    //   2608: iconst_0
    //   2609: iconst_0
    //   2610: invokestatic parseBoundSize : (Ljava/lang/String;ZZ)Lnet/miginfocom/layout/BoundSize;
    //   2613: invokevirtual setSize : (Lnet/miginfocom/layout/BoundSize;)V
    //   2616: goto -> 3309
    //   2619: aload #4
    //   2621: ldc 'wmin '
    //   2623: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2626: ifne -> 2659
    //   2629: aload #4
    //   2631: ldc 'wmax '
    //   2633: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2636: ifne -> 2659
    //   2639: aload #4
    //   2641: ldc 'hmin '
    //   2643: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2646: ifne -> 2659
    //   2649: aload #4
    //   2651: ldc 'hmax '
    //   2653: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2656: ifeq -> 2785
    //   2659: aload #4
    //   2661: iconst_5
    //   2662: invokevirtual substring : (I)Ljava/lang/String;
    //   2665: invokevirtual trim : ()Ljava/lang/String;
    //   2668: astore #8
    //   2670: aload #8
    //   2672: invokevirtual length : ()I
    //   2675: ifle -> 2785
    //   2678: aload #8
    //   2680: aconst_null
    //   2681: iload #7
    //   2683: invokestatic parseUnitValue : (Ljava/lang/String;Lnet/miginfocom/layout/UnitValue;Z)Lnet/miginfocom/layout/UnitValue;
    //   2686: astore #9
    //   2688: aload #4
    //   2690: iconst_3
    //   2691: invokevirtual charAt : (I)C
    //   2694: bipush #110
    //   2696: if_icmpne -> 2703
    //   2699: iconst_1
    //   2700: goto -> 2704
    //   2703: iconst_0
    //   2704: istore #10
    //   2706: iload #7
    //   2708: ifeq -> 2718
    //   2711: aload_1
    //   2712: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   2715: goto -> 2722
    //   2718: aload_1
    //   2719: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   2722: astore #11
    //   2724: aload #11
    //   2726: new net/miginfocom/layout/BoundSize
    //   2729: dup
    //   2730: iload #10
    //   2732: ifeq -> 2740
    //   2735: aload #9
    //   2737: goto -> 2748
    //   2740: aload #11
    //   2742: invokevirtual getSize : ()Lnet/miginfocom/layout/BoundSize;
    //   2745: invokevirtual getMin : ()Lnet/miginfocom/layout/UnitValue;
    //   2748: aload #11
    //   2750: invokevirtual getSize : ()Lnet/miginfocom/layout/BoundSize;
    //   2753: invokevirtual getPreferred : ()Lnet/miginfocom/layout/UnitValue;
    //   2756: iload #10
    //   2758: ifeq -> 2772
    //   2761: aload #11
    //   2763: invokevirtual getSize : ()Lnet/miginfocom/layout/BoundSize;
    //   2766: invokevirtual getMax : ()Lnet/miginfocom/layout/UnitValue;
    //   2769: goto -> 2774
    //   2772: aload #9
    //   2774: aload #8
    //   2776: invokespecial <init> : (Lnet/miginfocom/layout/UnitValue;Lnet/miginfocom/layout/UnitValue;Lnet/miginfocom/layout/UnitValue;Ljava/lang/String;)V
    //   2779: invokevirtual setSize : (Lnet/miginfocom/layout/BoundSize;)V
    //   2782: goto -> 3309
    //   2785: aload #4
    //   2787: ldc 'west'
    //   2789: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2792: ifeq -> 2803
    //   2795: aload_1
    //   2796: iconst_1
    //   2797: invokevirtual setDockSide : (I)V
    //   2800: goto -> 3309
    //   2803: aload #4
    //   2805: ldc 'hidemode '
    //   2807: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2810: ifeq -> 2830
    //   2813: aload_1
    //   2814: aload #4
    //   2816: bipush #9
    //   2818: invokevirtual substring : (I)Ljava/lang/String;
    //   2821: invokestatic parseInt : (Ljava/lang/String;)I
    //   2824: invokevirtual setHideMode : (I)V
    //   2827: goto -> 3309
    //   2830: iload #6
    //   2832: bipush #105
    //   2834: if_icmpne -> 2903
    //   2837: aload #4
    //   2839: ldc 'id '
    //   2841: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   2844: ifeq -> 2903
    //   2847: aload_1
    //   2848: aload #4
    //   2850: iconst_3
    //   2851: invokevirtual substring : (I)Ljava/lang/String;
    //   2854: invokevirtual trim : ()Ljava/lang/String;
    //   2857: invokevirtual setId : (Ljava/lang/String;)V
    //   2860: aload_1
    //   2861: invokevirtual getId : ()Ljava/lang/String;
    //   2864: bipush #46
    //   2866: invokevirtual indexOf : (I)I
    //   2869: istore #7
    //   2871: iload #7
    //   2873: ifeq -> 2890
    //   2876: iload #7
    //   2878: aload_1
    //   2879: invokevirtual getId : ()Ljava/lang/String;
    //   2882: invokevirtual length : ()I
    //   2885: iconst_1
    //   2886: isub
    //   2887: if_icmpne -> 2900
    //   2890: new java/lang/IllegalArgumentException
    //   2893: dup
    //   2894: ldc 'Dot must not be first or last!'
    //   2896: invokespecial <init> : (Ljava/lang/String;)V
    //   2899: athrow
    //   2900: goto -> 3309
    //   2903: iload #6
    //   2905: bipush #101
    //   2907: if_icmpne -> 3056
    //   2910: aload #4
    //   2912: ldc 'east'
    //   2914: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2917: ifeq -> 2928
    //   2920: aload_1
    //   2921: iconst_3
    //   2922: invokevirtual setDockSide : (I)V
    //   2925: goto -> 3309
    //   2928: aload #4
    //   2930: ldc 'external'
    //   2932: invokevirtual equals : (Ljava/lang/Object;)Z
    //   2935: ifeq -> 2946
    //   2938: aload_1
    //   2939: iconst_1
    //   2940: invokevirtual setExternal : (Z)V
    //   2943: goto -> 3309
    //   2946: aload #4
    //   2948: iconst_4
    //   2949: anewarray java/lang/String
    //   2952: dup
    //   2953: iconst_0
    //   2954: ldc 'endgroupx'
    //   2956: aastore
    //   2957: dup
    //   2958: iconst_1
    //   2959: ldc 'endgroupy'
    //   2961: aastore
    //   2962: dup
    //   2963: iconst_2
    //   2964: ldc 'egx'
    //   2966: aastore
    //   2967: dup
    //   2968: iconst_3
    //   2969: ldc 'egy'
    //   2971: aastore
    //   2972: iconst_4
    //   2973: newarray int
    //   2975: dup
    //   2976: iconst_0
    //   2977: iconst_m1
    //   2978: iastore
    //   2979: dup
    //   2980: iconst_1
    //   2981: iconst_m1
    //   2982: iastore
    //   2983: dup
    //   2984: iconst_2
    //   2985: iconst_m1
    //   2986: iastore
    //   2987: dup
    //   2988: iconst_3
    //   2989: iconst_m1
    //   2990: iastore
    //   2991: iconst_1
    //   2992: invokestatic startsWithLenient : (Ljava/lang/String;[Ljava/lang/String;[IZ)I
    //   2995: istore #5
    //   2997: iload #5
    //   2999: iconst_m1
    //   3000: if_icmple -> 3056
    //   3003: aload #4
    //   3005: iload #5
    //   3007: invokevirtual substring : (I)Ljava/lang/String;
    //   3010: invokevirtual trim : ()Ljava/lang/String;
    //   3013: astore #7
    //   3015: aload #4
    //   3017: iload #5
    //   3019: iconst_1
    //   3020: isub
    //   3021: invokevirtual charAt : (I)C
    //   3024: istore #8
    //   3026: iload #8
    //   3028: bipush #120
    //   3030: if_icmpne -> 3040
    //   3033: aload_1
    //   3034: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   3037: goto -> 3044
    //   3040: aload_1
    //   3041: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   3044: astore #9
    //   3046: aload #9
    //   3048: aload #7
    //   3050: invokevirtual setEndGroup : (Ljava/lang/String;)V
    //   3053: goto -> 3309
    //   3056: iload #6
    //   3058: bipush #100
    //   3060: if_icmpne -> 3206
    //   3063: aload #4
    //   3065: ldc 'dock north'
    //   3067: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3070: ifeq -> 3081
    //   3073: aload_1
    //   3074: iconst_0
    //   3075: invokevirtual setDockSide : (I)V
    //   3078: goto -> 3309
    //   3081: aload #4
    //   3083: ldc 'dock west'
    //   3085: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3088: ifeq -> 3099
    //   3091: aload_1
    //   3092: iconst_1
    //   3093: invokevirtual setDockSide : (I)V
    //   3096: goto -> 3309
    //   3099: aload #4
    //   3101: ldc 'dock south'
    //   3103: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3106: ifeq -> 3117
    //   3109: aload_1
    //   3110: iconst_2
    //   3111: invokevirtual setDockSide : (I)V
    //   3114: goto -> 3309
    //   3117: aload #4
    //   3119: ldc 'dock east'
    //   3121: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3124: ifeq -> 3135
    //   3127: aload_1
    //   3128: iconst_3
    //   3129: invokevirtual setDockSide : (I)V
    //   3132: goto -> 3309
    //   3135: aload #4
    //   3137: ldc 'dock center'
    //   3139: invokevirtual equals : (Ljava/lang/Object;)Z
    //   3142: ifeq -> 3206
    //   3145: aload_1
    //   3146: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   3149: new java/lang/Float
    //   3152: dup
    //   3153: ldc 100.0
    //   3155: invokespecial <init> : (F)V
    //   3158: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   3161: aload_1
    //   3162: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   3165: new java/lang/Float
    //   3168: dup
    //   3169: ldc 100.0
    //   3171: invokespecial <init> : (F)V
    //   3174: invokevirtual setGrow : (Ljava/lang/Float;)V
    //   3177: aload_1
    //   3178: new java/lang/Float
    //   3181: dup
    //   3182: ldc 100.0
    //   3184: invokespecial <init> : (F)V
    //   3187: invokevirtual setPushX : (Ljava/lang/Float;)V
    //   3190: aload_1
    //   3191: new java/lang/Float
    //   3194: dup
    //   3195: ldc 100.0
    //   3197: invokespecial <init> : (F)V
    //   3200: invokevirtual setPushY : (Ljava/lang/Float;)V
    //   3203: goto -> 3309
    //   3206: aload #4
    //   3208: iconst_1
    //   3209: invokestatic parseAlignKeywords : (Ljava/lang/String;Z)Lnet/miginfocom/layout/UnitValue;
    //   3212: astore #7
    //   3214: aload #7
    //   3216: ifnull -> 3231
    //   3219: aload_1
    //   3220: invokevirtual getHorizontal : ()Lnet/miginfocom/layout/DimConstraint;
    //   3223: aload #7
    //   3225: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   3228: goto -> 3309
    //   3231: aload #4
    //   3233: iconst_0
    //   3234: invokestatic parseAlignKeywords : (Ljava/lang/String;Z)Lnet/miginfocom/layout/UnitValue;
    //   3237: astore #8
    //   3239: aload #8
    //   3241: ifnull -> 3256
    //   3244: aload_1
    //   3245: invokevirtual getVertical : ()Lnet/miginfocom/layout/DimConstraint;
    //   3248: aload #8
    //   3250: invokevirtual setAlign : (Lnet/miginfocom/layout/UnitValue;)V
    //   3253: goto -> 3309
    //   3256: new java/lang/IllegalArgumentException
    //   3259: dup
    //   3260: ldc 'Unknown keyword.'
    //   3262: invokespecial <init> : (Ljava/lang/String;)V
    //   3265: athrow
    //   3266: astore #5
    //   3268: new java/lang/IllegalArgumentException
    //   3271: dup
    //   3272: new java/lang/StringBuilder
    //   3275: dup
    //   3276: invokespecial <init> : ()V
    //   3279: ldc 'Illegal Constraint: ''
    //   3281: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3284: aload #4
    //   3286: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3289: ldc ''\\n'
    //   3291: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3294: aload #5
    //   3296: invokevirtual getMessage : ()Ljava/lang/String;
    //   3299: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   3302: invokevirtual toString : ()Ljava/lang/String;
    //   3305: invokespecial <init> : (Ljava/lang/String;)V
    //   3308: athrow
    //   3309: iinc #3, 1
    //   3312: goto -> 26
    //   3315: aload_1
    //   3316: areturn
    // Exception table:
    //   from	to	target	type
    //   37	45	3266	java/lang/Exception
    //   48	81	3266	java/lang/Exception
    //   84	99	3266	java/lang/Exception
    //   102	135	3266	java/lang/Exception
    //   138	189	3266	java/lang/Exception
    //   192	249	3266	java/lang/Exception
    //   252	303	3266	java/lang/Exception
    //   306	321	3266	java/lang/Exception
    //   324	380	3266	java/lang/Exception
    //   383	439	3266	java/lang/Exception
    //   442	524	3266	java/lang/Exception
    //   527	568	3266	java/lang/Exception
    //   571	612	3266	java/lang/Exception
    //   615	703	3266	java/lang/Exception
    //   706	858	3266	java/lang/Exception
    //   861	975	3266	java/lang/Exception
    //   978	1025	3266	java/lang/Exception
    //   1028	1068	3266	java/lang/Exception
    //   1071	1151	3266	java/lang/Exception
    //   1154	1318	3266	java/lang/Exception
    //   1321	1410	3266	java/lang/Exception
    //   1413	1482	3266	java/lang/Exception
    //   1485	1547	3266	java/lang/Exception
    //   1550	1623	3266	java/lang/Exception
    //   1626	1791	3266	java/lang/Exception
    //   1794	1928	3266	java/lang/Exception
    //   1931	2094	3266	java/lang/Exception
    //   2097	2199	3266	java/lang/Exception
    //   2202	2239	3266	java/lang/Exception
    //   2242	2279	3266	java/lang/Exception
    //   2282	2356	3266	java/lang/Exception
    //   2359	2397	3266	java/lang/Exception
    //   2400	2429	3266	java/lang/Exception
    //   2432	2464	3266	java/lang/Exception
    //   2467	2547	3266	java/lang/Exception
    //   2550	2616	3266	java/lang/Exception
    //   2619	2782	3266	java/lang/Exception
    //   2785	2800	3266	java/lang/Exception
    //   2803	2827	3266	java/lang/Exception
    //   2830	2900	3266	java/lang/Exception
    //   2903	2925	3266	java/lang/Exception
    //   2928	2943	3266	java/lang/Exception
    //   2946	3053	3266	java/lang/Exception
    //   3056	3078	3266	java/lang/Exception
    //   3081	3096	3266	java/lang/Exception
    //   3099	3114	3266	java/lang/Exception
    //   3117	3132	3266	java/lang/Exception
    //   3135	3203	3266	java/lang/Exception
    //   3206	3228	3266	java/lang/Exception
    //   3231	3253	3266	java/lang/Exception
    //   3256	3266	3266	java/lang/Exception }
  
  public static UnitValue[] parseInsets(String paramString, boolean paramBoolean) {
    if (paramString.length() == 0 || paramString.equals("dialog") || paramString.equals("panel")) {
      if (!paramBoolean)
        throw new IllegalAccessError("Insets now allowed: " + paramString + "\n"); 
      boolean bool = paramString.startsWith("p");
      UnitValue[] arrayOfUnitValue1 = new UnitValue[4];
      for (byte b1 = 0; b1 < 4; b1++)
        arrayOfUnitValue1[b1] = bool ? PlatformDefaults.getPanelInsets(b1) : PlatformDefaults.getDialogInsets(b1); 
      return arrayOfUnitValue1;
    } 
    String[] arrayOfString = toTrimmedTokens(paramString, ' ');
    UnitValue[] arrayOfUnitValue = new UnitValue[4];
    for (byte b = 0; b < 4; b++) {
      UnitValue unitValue = parseUnitValue(arrayOfString[(b < arrayOfString.length) ? b : (arrayOfString.length - 1)], UnitValue.ZERO, (b % 2 == 1));
      arrayOfUnitValue[b] = (unitValue != null) ? unitValue : PlatformDefaults.getPanelInsets(b);
    } 
    return arrayOfUnitValue;
  }
  
  private static BoundSize[] parseGaps(String paramString) {
    BoundSize[] arrayOfBoundSize = new BoundSize[4];
    int i = startsWithLenient(paramString, "gaptop", -1, true);
    if (i > -1) {
      paramString = paramString.substring(i).trim();
      arrayOfBoundSize[0] = parseBoundSize(paramString, true, false);
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, "gapleft", -1, true);
    if (i > -1) {
      paramString = paramString.substring(i).trim();
      arrayOfBoundSize[1] = parseBoundSize(paramString, true, true);
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, "gapbottom", -1, true);
    if (i > -1) {
      paramString = paramString.substring(i).trim();
      arrayOfBoundSize[2] = parseBoundSize(paramString, true, false);
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, "gapright", -1, true);
    if (i > -1) {
      paramString = paramString.substring(i).trim();
      arrayOfBoundSize[3] = parseBoundSize(paramString, true, true);
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, "gapbefore", -1, true);
    if (i > -1) {
      paramString = paramString.substring(i).trim();
      arrayOfBoundSize[1] = parseBoundSize(paramString, true, true);
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, "gapafter", -1, true);
    if (i > -1) {
      paramString = paramString.substring(i).trim();
      arrayOfBoundSize[3] = parseBoundSize(paramString, true, true);
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, new String[] { "gapx", "gapy" }, null, true);
    if (i > -1) {
      boolean bool = (paramString.charAt(3) == 'x');
      String[] arrayOfString = toTrimmedTokens(paramString.substring(i).trim(), ' ');
      arrayOfBoundSize[bool ? 1 : 0] = parseBoundSize(arrayOfString[0], true, bool);
      if (arrayOfString.length > 1)
        arrayOfBoundSize[bool ? 3 : 2] = parseBoundSize(arrayOfString[1], true, !bool); 
      return arrayOfBoundSize;
    } 
    i = startsWithLenient(paramString, "gap ", 1, true);
    if (i > -1) {
      String[] arrayOfString = toTrimmedTokens(paramString.substring(i).trim(), ' ');
      arrayOfBoundSize[1] = parseBoundSize(arrayOfString[0], true, true);
      if (arrayOfString.length > 1) {
        arrayOfBoundSize[3] = parseBoundSize(arrayOfString[1], true, false);
        if (arrayOfString.length > 2) {
          arrayOfBoundSize[0] = parseBoundSize(arrayOfString[2], true, true);
          if (arrayOfString.length > 3)
            arrayOfBoundSize[2] = parseBoundSize(arrayOfString[3], true, false); 
        } 
      } 
      return arrayOfBoundSize;
    } 
    throw new IllegalArgumentException("Unknown Gap part: '" + paramString + "'");
  }
  
  private static int parseSpan(String paramString) { return (paramString.length() > 0) ? Integer.parseInt(paramString) : 2097051; }
  
  private static Float parseFloat(String paramString, Float paramFloat) { return (paramString.length() > 0) ? new Float(Float.parseFloat(paramString)) : paramFloat; }
  
  public static BoundSize parseBoundSize(String paramString, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramString.length() == 0 || paramString.equals("null") || paramString.equals("n"))
      return null; 
    String str1 = paramString;
    boolean bool = false;
    if (paramString.endsWith("push")) {
      bool = true;
      int i = paramString.length();
      paramString = paramString.substring(0, i - (paramString.endsWith(":push") ? 5 : 4));
      if (paramString.length() == 0)
        return new BoundSize(null, null, null, true, str1); 
    } 
    String[] arrayOfString = toTrimmedTokens(paramString, ':');
    String str2 = arrayOfString[0];
    if (arrayOfString.length == 1) {
      boolean bool1 = str2.endsWith("!");
      if (bool1)
        str2 = str2.substring(0, str2.length() - 1); 
      UnitValue unitValue = parseUnitValue(str2, null, paramBoolean2);
      return new BoundSize((paramBoolean1 || bool1) ? unitValue : null, unitValue, bool1 ? unitValue : null, bool, str1);
    } 
    if (arrayOfString.length == 2)
      return new BoundSize(parseUnitValue(str2, null, paramBoolean2), parseUnitValue(arrayOfString[1], null, paramBoolean2), null, bool, str1); 
    if (arrayOfString.length == 3)
      return new BoundSize(parseUnitValue(str2, null, paramBoolean2), parseUnitValue(arrayOfString[1], null, paramBoolean2), parseUnitValue(arrayOfString[2], null, paramBoolean2), bool, str1); 
    throw new IllegalArgumentException("Min:Preferred:Max size section must contain 0, 1 or 2 colons. '" + str1 + "'");
  }
  
  public static UnitValue parseUnitValueOrAlign(String paramString, boolean paramBoolean, UnitValue paramUnitValue) {
    if (paramString.length() == 0)
      return paramUnitValue; 
    UnitValue unitValue = parseAlignKeywords(paramString, paramBoolean);
    return (unitValue != null) ? unitValue : parseUnitValue(paramString, paramUnitValue, paramBoolean);
  }
  
  public static UnitValue parseUnitValue(String paramString, boolean paramBoolean) { return parseUnitValue(paramString, null, paramBoolean); }
  
  private static UnitValue parseUnitValue(String paramString, UnitValue paramUnitValue, boolean paramBoolean) {
    if (paramString == null || paramString.length() == 0)
      return paramUnitValue; 
    String str = paramString;
    char c = paramString.charAt(0);
    if (c == '(' && paramString.charAt(paramString.length() - 1) == ')')
      paramString = paramString.substring(1, paramString.length() - 1); 
    if (c == 'n' && (paramString.equals("null") || paramString.equals("n")))
      return null; 
    if (c == 'i' && paramString.equals("inf"))
      return UnitValue.INF; 
    int i = getOper(paramString);
    boolean bool = (i == 101 || i == 102 || i == 103 || i == 104) ? 1 : 0;
    if (i != 100) {
      String[] arrayOfString;
      if (!bool) {
        String str1 = paramString.substring(4, paramString.length() - 1).trim();
        arrayOfString = toTrimmedTokens(str1, ',');
        if (arrayOfString.length == 1)
          return parseUnitValue(str1, null, paramBoolean); 
      } else {
        char c1;
        if (i == 101) {
          c1 = '+';
        } else if (i == 102) {
          c1 = '-';
        } else if (i == 103) {
          c1 = '*';
        } else {
          c1 = '/';
        } 
        arrayOfString = toTrimmedTokens(paramString, c1);
        if (arrayOfString.length > 2) {
          String str1 = arrayOfString[arrayOfString.length - 1];
          String str2 = paramString.substring(0, paramString.length() - str1.length() - 1);
          arrayOfString = new String[] { str2, str1 };
        } 
      } 
      if (arrayOfString.length != 2)
        throw new IllegalArgumentException("Malformed UnitValue: '" + paramString + "'"); 
      UnitValue unitValue1 = parseUnitValue(arrayOfString[0], null, paramBoolean);
      UnitValue unitValue2 = parseUnitValue(arrayOfString[1], null, paramBoolean);
      if (unitValue1 == null || unitValue2 == null)
        throw new IllegalArgumentException("Malformed UnitValue. Must be two sub-values: '" + paramString + "'"); 
      return new UnitValue(paramBoolean, i, unitValue1, unitValue2, str);
    } 
    try {
      String[] arrayOfString = getNumTextParts(paramString);
      float f = (arrayOfString[0].length() > 0) ? Float.parseFloat(arrayOfString[0]) : 1.0F;
      return new UnitValue(f, arrayOfString[1], paramBoolean, i, str);
    } catch (Exception exception) {
      throw new IllegalArgumentException("Malformed UnitValue: '" + paramString + "'");
    } 
  }
  
  static UnitValue parseAlignKeywords(String paramString, boolean paramBoolean) {
    if (startsWithLenient(paramString, "center", 1, false) != -1)
      return UnitValue.CENTER; 
    if (paramBoolean) {
      if (startsWithLenient(paramString, "left", 1, false) != -1)
        return UnitValue.LEFT; 
      if (startsWithLenient(paramString, "right", 1, false) != -1)
        return UnitValue.RIGHT; 
      if (startsWithLenient(paramString, "leading", 4, false) != -1)
        return UnitValue.LEADING; 
      if (startsWithLenient(paramString, "trailing", 5, false) != -1)
        return UnitValue.TRAILING; 
      if (startsWithLenient(paramString, "label", 5, false) != -1)
        return UnitValue.LABEL; 
    } else {
      if (startsWithLenient(paramString, "baseline", 4, false) != -1)
        return UnitValue.BASELINE_IDENTITY; 
      if (startsWithLenient(paramString, "top", 1, false) != -1)
        return UnitValue.TOP; 
      if (startsWithLenient(paramString, "bottom", 1, false) != -1)
        return UnitValue.BOTTOM; 
    } 
    return null;
  }
  
  private static String[] getNumTextParts(String paramString) {
    byte b = 0;
    int i = paramString.length();
    while (b < i) {
      char c = paramString.charAt(b);
      if (c == ' ')
        throw new IllegalArgumentException("Space in UnitValue: '" + paramString + "'"); 
      if ((c < '0' || c > '9') && c != '.' && c != '-')
        return new String[] { paramString.substring(0, b).trim(), paramString.substring(b).trim() }; 
      b++;
    } 
    return new String[] { paramString, "" };
  }
  
  private static int getOper(String paramString) {
    int i = paramString.length();
    if (i < 3)
      return 100; 
    if (i > 5 && paramString.charAt(3) == '(' && paramString.charAt(i - 1) == ')') {
      if (paramString.startsWith("min("))
        return 105; 
      if (paramString.startsWith("max("))
        return 106; 
      if (paramString.startsWith("mid("))
        return 107; 
    } 
    for (byte b = 0; b < 2; b++) {
      int j = i - 1;
      byte b1 = 0;
      while (j > 0) {
        char c = paramString.charAt(j);
        if (c == ')') {
          b1++;
        } else if (c == '(') {
          b1--;
        } else if (b1 == 0) {
          if (!b) {
            if (c == '+')
              return 101; 
            if (c == '-')
              return 102; 
          } else {
            if (c == '*')
              return 103; 
            if (c == '/')
              return 104; 
          } 
        } 
        j--;
      } 
    } 
    return 100;
  }
  
  private static int startsWithLenient(String paramString, String[] paramArrayOfString, int[] paramArrayOfInt, boolean paramBoolean) {
    for (byte b = 0; b < paramArrayOfString.length; b++) {
      int i = (paramArrayOfInt != null) ? paramArrayOfInt[b] : -1;
      int j = startsWithLenient(paramString, paramArrayOfString[b], i, paramBoolean);
      if (j > -1)
        return j; 
    } 
    return -1;
  }
  
  private static int startsWithLenient(String paramString1, String paramString2, int paramInt, boolean paramBoolean) {
    if (paramString1.charAt(0) != paramString2.charAt(0))
      return -1; 
    if (paramInt == -1)
      paramInt = paramString2.length(); 
    int i = paramString1.length();
    if (i < paramInt)
      return -1; 
    int j = paramString2.length();
    byte b1 = 0;
    for (byte b2 = 0; b2 < j; b2++) {
      while (b1 < i && (paramString1.charAt(b1) == ' ' || paramString1.charAt(b1) == '_'))
        b1++; 
      if (b1 >= i || paramString1.charAt(b1) != paramString2.charAt(b2))
        return (b2 >= paramInt && (paramBoolean || b1 >= i) && (b1 >= i || paramString1.charAt(b1 - 1) == ' ')) ? b1 : -1; 
      b1++;
    } 
    return (b1 >= i || paramBoolean || paramString1.charAt(b1) == ' ') ? b1 : -1;
  }
  
  private static String[] toTrimmedTokens(String paramString, char paramChar) {
    byte b1 = 0;
    int i = paramString.length();
    boolean bool = (paramChar == ' ') ? 1 : 0;
    byte b2 = 0;
    for (null = 0; null < i; null++) {
      char c = paramString.charAt(null);
      if (c == '(') {
        b2++;
      } else if (c == ')') {
        b2--;
      } else if (b2 == 0 && c == paramChar) {
        b1++;
        while (bool && null < i - 1 && paramString.charAt(null + 1) == ' ')
          null++; 
      } 
      if (b2 < 0)
        throw new IllegalArgumentException("Unbalanced parentheses: '" + paramString + "'"); 
    } 
    if (b2 != 0)
      throw new IllegalArgumentException("Unbalanced parentheses: '" + paramString + "'"); 
    if (b1 == 0)
      return new String[] { paramString.trim() }; 
    String[] arrayOfString = new String[b1 + 1];
    byte b3 = 0;
    byte b4 = 0;
    b2 = 0;
    for (byte b5 = 0; b5 < i; b5++) {
      char c = paramString.charAt(b5);
      if (c == '(') {
        b2++;
      } else if (c == ')') {
        b2--;
      } else if (b2 == 0 && c == paramChar) {
        arrayOfString[b4++] = paramString.substring(b3, b5).trim();
        b3 = b5 + 1;
        while (bool && b5 < i - 1 && paramString.charAt(b5 + 1) == ' ')
          b5++; 
      } 
    } 
    arrayOfString[b4++] = paramString.substring(b3, i).trim();
    return arrayOfString;
  }
  
  private static final ArrayList<String> getRowColAndGapsTrimmed(String paramString) { // Byte code:
    //   0: aload_0
    //   1: bipush #124
    //   3: invokevirtual indexOf : (I)I
    //   6: iconst_m1
    //   7: if_icmpeq -> 21
    //   10: aload_0
    //   11: ldc_w '\|'
    //   14: ldc_w ']['
    //   17: invokevirtual replaceAll : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   20: astore_0
    //   21: new java/util/ArrayList
    //   24: dup
    //   25: aload_0
    //   26: invokevirtual length : ()I
    //   29: iconst_3
    //   30: ishr
    //   31: iconst_3
    //   32: invokestatic max : (II)I
    //   35: invokespecial <init> : (I)V
    //   38: astore_1
    //   39: iconst_0
    //   40: istore_2
    //   41: iconst_0
    //   42: istore_3
    //   43: iconst_0
    //   44: istore #4
    //   46: iconst_0
    //   47: istore #5
    //   49: aload_0
    //   50: invokevirtual length : ()I
    //   53: istore #6
    //   55: iload #5
    //   57: iload #6
    //   59: if_icmpge -> 136
    //   62: aload_0
    //   63: iload #5
    //   65: invokevirtual charAt : (I)C
    //   68: istore #7
    //   70: iload #7
    //   72: bipush #91
    //   74: if_icmpne -> 83
    //   77: iinc #2, 1
    //   80: goto -> 93
    //   83: iload #7
    //   85: bipush #93
    //   87: if_icmpne -> 130
    //   90: iinc #3, 1
    //   93: iload_2
    //   94: iload_3
    //   95: if_icmpeq -> 108
    //   98: iload_2
    //   99: iconst_1
    //   100: isub
    //   101: iload_3
    //   102: if_icmpeq -> 108
    //   105: goto -> 136
    //   108: aload_1
    //   109: aload_0
    //   110: iload #4
    //   112: iload #5
    //   114: invokevirtual substring : (II)Ljava/lang/String;
    //   117: invokevirtual trim : ()Ljava/lang/String;
    //   120: invokevirtual add : (Ljava/lang/Object;)Z
    //   123: pop
    //   124: iload #5
    //   126: iconst_1
    //   127: iadd
    //   128: istore #4
    //   130: iinc #5, 1
    //   133: goto -> 55
    //   136: iload_2
    //   137: iload_3
    //   138: if_icmpeq -> 169
    //   141: new java/lang/IllegalArgumentException
    //   144: dup
    //   145: new java/lang/StringBuilder
    //   148: dup
    //   149: invokespecial <init> : ()V
    //   152: ldc_w ''[' and ']' mismatch in row/column format string: '
    //   155: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: aload_0
    //   159: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: invokevirtual toString : ()Ljava/lang/String;
    //   165: invokespecial <init> : (Ljava/lang/String;)V
    //   168: athrow
    //   169: iload_2
    //   170: ifne -> 196
    //   173: aload_1
    //   174: ldc ''
    //   176: invokevirtual add : (Ljava/lang/Object;)Z
    //   179: pop
    //   180: aload_1
    //   181: aload_0
    //   182: invokevirtual add : (Ljava/lang/Object;)Z
    //   185: pop
    //   186: aload_1
    //   187: ldc ''
    //   189: invokevirtual add : (Ljava/lang/Object;)Z
    //   192: pop
    //   193: goto -> 220
    //   196: aload_1
    //   197: invokevirtual size : ()I
    //   200: iconst_2
    //   201: irem
    //   202: ifne -> 220
    //   205: aload_1
    //   206: aload_0
    //   207: iload #4
    //   209: aload_0
    //   210: invokevirtual length : ()I
    //   213: invokevirtual substring : (II)Ljava/lang/String;
    //   216: invokevirtual add : (Ljava/lang/Object;)Z
    //   219: pop
    //   220: aload_1
    //   221: areturn }
  
  public static final String prepare(String paramString) { return (paramString != null) ? paramString.trim().toLowerCase() : ""; }
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\miginfocom\layout\ConstraintParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */