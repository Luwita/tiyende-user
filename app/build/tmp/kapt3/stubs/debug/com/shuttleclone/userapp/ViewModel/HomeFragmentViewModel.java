package com.shuttleclone.userapp.ViewModel;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u00aa\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0018\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002Jf\u0010\u0005\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u0097\u0001\u001a\u00020\u00042\u0007\u0010\u0098\u0001\u001a\u00020\u00042\u0007\u0010\u0099\u0001\u001a\u00020\u00042\u0007\u0010\u009a\u0001\u001a\u00020\u00042\u0007\u0010\u009b\u0001\u001a\u00020\u00042\u0007\u0010\u009c\u0001\u001a\u00020\u00042\u0007\u0010\u009d\u0001\u001a\u00020\u00042\u0007\u0010\u009e\u0001\u001a\u00020\u0004Jp\u0010\u009f\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00a0\u0001\u001a\u00020\u00042\u0007\u0010\u00a1\u0001\u001a\u00020\u00042\u0007\u0010\u00a2\u0001\u001a\u00020\u00042\u0007\u0010\u00a3\u0001\u001a\u00020\u00042\u0007\u0010\u00a4\u0001\u001a\u00020\u00042\u0007\u0010\u00a5\u0001\u001a\u00020\u00042\u0007\u0010\u00a6\u0001\u001a\u00020\u00042\u0007\u0010\u00a7\u0001\u001a\u00020\u00042\u0007\u0010\u00a8\u0001\u001a\u00020\u0004J3\u0010\u00a9\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00aa\u0001\u001a\u00020\u00042\t\u0010\u00a7\u0001\u001a\u0004\u0018\u00010\u0004J\u001c\u0010\u00ab\u0001\u001a\u00030\u0093\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u00012\b\u0010\u00ac\u0001\u001a\u00030\u00ad\u0001J\u001e\u0010%\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004J\u001f\u0010\u00ae\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004J+\u0010\u00af\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\n\u0010\u00b0\u0001\u001a\u0005\u0018\u00010\u00b1\u0001J\u001e\u0010,\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004J.\u00100\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00042\u0007\u0010\u00b2\u0001\u001a\u00020\u00042\u0007\u0010\u00b3\u0001\u001a\u00020\u0004J;\u0010\u00b4\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\b\u0010\u00b5\u0001\u001a\u00030\u00b6\u00012\n\u0010\u00b7\u0001\u001a\u0005\u0018\u00010\u00b6\u0001\u00a2\u0006\u0003\u0010\u00b8\u0001J2\u00105\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\b\u0010\u00b9\u0001\u001a\u00030\u00b6\u00012\b\u0010\u00ba\u0001\u001a\u00030\u00b6\u0001JH\u0010\u00bb\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\n\u0010\u00bc\u0001\u001a\u0005\u0018\u00010\u00b6\u00012\n\u0010\u00ba\u0001\u001a\u0005\u0018\u00010\u00b6\u00012\t\u0010\u00bd\u0001\u001a\u0004\u0018\u00010\u0004\u00a2\u0006\u0003\u0010\u00be\u0001J\u001c\u00109\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u0004J\u001e\u0010d\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004J\u001f\u0010\u00bf\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004J\u001c\u0010=\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u0004Jf\u0010A\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00a0\u0001\u001a\u00020\u00042\u0007\u0010\u00a1\u0001\u001a\u00020\u00042\u0007\u0010\u00a2\u0001\u001a\u00020\u00042\u0007\u0010\u00a3\u0001\u001a\u00020\u00042\u0007\u0010\u00a4\u0001\u001a\u00020\u00042\u0007\u0010\u00c0\u0001\u001a\u00020\u00042\u0007\u0010\u00c1\u0001\u001a\u00020\u00042\u0007\u0010\u00a6\u0001\u001a\u00020\u0004J5\u0010\u00c2\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00c3\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00ba\u0001\u001a\u0004\u0018\u00010\u0004J\u001e\u0010H\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u0004J8\u0010\u00c4\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00042\u0007\u0010\u00c5\u0001\u001a\u00020\u00042\u0007\u0010\u00c6\u0001\u001a\u00020\u00042\u0007\u0010\u00c7\u0001\u001a\u00020\u0004J\u009c\u0001\u0010O\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00a5\u0001\u001a\u00020\u00042\u0007\u0010\u00c8\u0001\u001a\u00020\u00042\u0007\u0010\u00a0\u0001\u001a\u00020\u00042\u0007\u0010\u00a1\u0001\u001a\u00020\u00042\u0007\u0010\u00a2\u0001\u001a\u00020\u00042\u0007\u0010\u00a3\u0001\u001a\u00020\u00042\u0007\u0010\u00a4\u0001\u001a\u00020\u00042\u0007\u0010\u00c9\u0001\u001a\u00020\u00042\u0007\u0010\u00ca\u0001\u001a\u00020\u00042\u0007\u0010\u00cb\u0001\u001a\u00020\u00042\u0007\u0010\u00c0\u0001\u001a\u00020\u00042\u0007\u0010\u00a6\u0001\u001a\u00020\u00042\u0007\u0010\u00cc\u0001\u001a\u00020\u00042\u0007\u0010\u00cd\u0001\u001a\u00020\u0004JL\u0010V\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00a5\u0001\u001a\u00020\u00042\u0007\u0010\u00c8\u0001\u001a\u00020\u00042\b\u0010\u00ce\u0001\u001a\u00030\u00cf\u00012\u0007\u0010\u00cc\u0001\u001a\u00020\u00042\u0007\u0010\u00aa\u0001\u001a\u00020\u0004J:\u0010Y\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00a5\u0001\u001a\u00020\u00042\u0007\u0010\u00c8\u0001\u001a\u00020\u00042\b\u0010\u00ce\u0001\u001a\u00030\u00cf\u0001J)\u0010_\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00d0\u0001\u001a\u0004\u0018\u00010\u0004J\n\u0010\u00d1\u0001\u001a\u00030\u0093\u0001H\u0014JC\u0010\u00d2\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00aa\u0001\u001a\u00020\u00042\u0007\u0010\u00ce\u0001\u001a\u00020\u00042\u0007\u0010\u00cc\u0001\u001a\u00020\u00042\u0007\u0010\u00cd\u0001\u001a\u00020\u0004J9\u0010j\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u00d3\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00d4\u0001\u001a\u00020\u00042\u0007\u0010\u00d5\u0001\u001a\u00020\u00042\u0007\u0010\u00d6\u0001\u001a\u00020\u0004J9\u0010n\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00d7\u0001\u001a\u00020\u00042\u0007\u0010\u00a3\u0001\u001a\u00020\u00042\u0007\u0010\u00a4\u0001\u001a\u00020\u0004J+\u0010\u00d8\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\n\u0010\u00d9\u0001\u001a\u0005\u0018\u00010\u00da\u0001J5\u0010\u00db\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00c3\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00ba\u0001\u001a\u0004\u0018\u00010\u0004J\u0082\u0001\u0010\u00dc\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00dd\u0001\u001a\u00020\u00042\u0007\u0010\u00de\u0001\u001a\u00020\u00042\u0007\u0010\u00df\u0001\u001a\u00020\u00042\u0007\u0010\u00e0\u0001\u001a\u00020\u00042\u0007\u0010\u00a7\u0001\u001a\u00020\u00042\u0007\u0010\u00e1\u0001\u001a\u00020\u00042\u0007\u0010\u00a8\u0001\u001a\u00020\u00042\u0007\u0010\u00a5\u0001\u001a\u00020\u00042\u0007\u0010\u00e2\u0001\u001a\u00020\u00042\u0007\u0010\u00e3\u0001\u001a\u00020\u00042\u0007\u0010\u00a6\u0001\u001a\u00020\u0004JK\u0010|\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\u0007\u0010\u00aa\u0001\u001a\u00020\u00042\u0019\u0010\u00e4\u0001\u001a\u0014\u0012\u0004\u0012\u00020\u00040\u00e5\u0001j\t\u0012\u0004\u0012\u00020\u0004`\u00e6\u00012\u0007\u0010\u00e7\u0001\u001a\u00020\u0004Ju\u0010\u00e8\u0001\u001a\u00030\u0093\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00042\u0007\u0010\u00e9\u0001\u001a\u00020\u00042\u0007\u0010\u00ea\u0001\u001a\u00020\u00042\u0007\u0010\u00eb\u0001\u001a\u00020\u00042\u0007\u0010\u00ec\u0001\u001a\u00020\u00042\u0007\u0010\u00ed\u0001\u001a\u00020\u00042\u0007\u0010\u00ee\u0001\u001a\u00020\u00042\u0007\u0010\u00ef\u0001\u001a\u00020\u00042\u0007\u0010\u00f0\u0001\u001a\u00020\u00042\u0007\u0010\u00f1\u0001\u001a\u00020\u00042\u0007\u0010\u00f2\u0001\u001a\u00020\u0004J*\u0010\u0082\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\t\u0010\u00aa\u0001\u001a\u0004\u0018\u00010\u0004J&\u0010\u0086\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00042\u0007\u0010\u00f3\u0001\u001a\u00020\u0004J\u00cb\u0001\u0010\u0089\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0096\u0001\u001a\u0004\u0018\u00010\u00042\n\u0010\u00f4\u0001\u001a\u0005\u0018\u00010\u00f5\u00012\b\u0010\u00f6\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00f8\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00f9\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00fa\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00fb\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00fc\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00fd\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00fe\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00ff\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00ed\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00ee\u0001\u001a\u00030\u00f7\u00012\b\u0010\u0080\u0002\u001a\u00030\u00f7\u00012\b\u0010\u00ea\u0001\u001a\u00030\u00f7\u00012\b\u0010\u00eb\u0001\u001a\u00030\u00f7\u00012\b\u0010\u0081\u0002\u001a\u00030\u00f7\u00012\b\u0010\u0082\u0002\u001a\u00030\u00f7\u0001J*\u0010\u0083\u0002\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\t\u0010\u0084\u0002\u001a\u0004\u0018\u00010\u00042\t\u0010\u0085\u0002\u001a\u0004\u0018\u00010\u0004JL\u0010\u008f\u0001\u001a\u00030\u0093\u00012\n\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0095\u00012\u0007\u0010\u0096\u0001\u001a\u00020\u00042\u0007\u0010\u00fe\u0001\u001a\u00020\u00042\b\u0010\u0086\u0002\u001a\u00030\u00b6\u00012\b\u0010\u0087\u0002\u001a\u00030\u00b6\u00012\u0007\u0010\u0088\u0002\u001a\u00020]2\u0007\u0010\u0089\u0002\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R#\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\tR\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0010\u0010\u000b\u001a\u0004\b\u000e\u0010\u000fR#\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0014\u0010\u000b\u001a\u0004\b\u0013\u0010\tR#\u0010\u0015\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0018\u0010\u000b\u001a\u0004\b\u0017\u0010\tR#\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001a0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001c\u0010\u000b\u001a\u0004\b\u001b\u0010\tR#\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b \u0010\u000b\u001a\u0004\b\u001f\u0010\tR#\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b$\u0010\u000b\u001a\u0004\b#\u0010\tR#\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010&0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b(\u0010\u000b\u001a\u0004\b\'\u0010\tR#\u0010)\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b+\u0010\u000b\u001a\u0004\b*\u0010\tR#\u0010,\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010-0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b/\u0010\u000b\u001a\u0004\b.\u0010\tR)\u00100\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000202\u0018\u0001010\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b4\u0010\u000b\u001a\u0004\b3\u0010\tR#\u00105\u001a\n\u0012\u0006\u0012\u0004\u0018\u0001060\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b8\u0010\u000b\u001a\u0004\b7\u0010\tR#\u00109\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010:0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b<\u0010\u000b\u001a\u0004\b;\u0010\tR#\u0010=\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010>0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b@\u0010\u000b\u001a\u0004\b?\u0010\tR#\u0010A\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010B0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bD\u0010\u000b\u001a\u0004\bC\u0010\tR#\u0010E\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bG\u0010\u000b\u001a\u0004\bF\u0010\tR#\u0010H\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010I0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bK\u0010\u000b\u001a\u0004\bJ\u0010\tR#\u0010L\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bN\u0010\u000b\u001a\u0004\bM\u0010\tR#\u0010O\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010P0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bR\u0010\u000b\u001a\u0004\bQ\u0010\tR#\u0010S\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010P0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bU\u0010\u000b\u001a\u0004\bT\u0010\tR#\u0010V\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010P0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bX\u0010\u000b\u001a\u0004\bW\u0010\tR#\u0010Y\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010P0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b[\u0010\u000b\u001a\u0004\bZ\u0010\tR!\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b^\u0010\u000b\u001a\u0004\b\\\u0010\tR#\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\ba\u0010\u000b\u001a\u0004\b`\u0010\tR#\u0010b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010c0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\be\u0010\u000b\u001a\u0004\bd\u0010\tR#\u0010f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010g0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bi\u0010\u000b\u001a\u0004\bh\u0010\tR#\u0010j\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010k0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bm\u0010\u000b\u001a\u0004\bl\u0010\tR#\u0010n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010o0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bq\u0010\u000b\u001a\u0004\bp\u0010\tR)\u0010r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000202\u0018\u0001010\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bt\u0010\u000b\u001a\u0004\bs\u0010\tR)\u0010u\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u000202\u0018\u0001010\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\bw\u0010\u000b\u001a\u0004\bv\u0010\tR#\u0010x\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010y0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b{\u0010\u000b\u001a\u0004\bz\u0010\tR#\u0010|\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b~\u0010\u000b\u001a\u0004\b}\u0010\tR%\u0010\u007f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0005\b\u0081\u0001\u0010\u000b\u001a\u0005\b\u0080\u0001\u0010\tR\'\u0010\u0082\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u00010\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0005\b\u0085\u0001\u0010\u000b\u001a\u0005\b\u0084\u0001\u0010\tR&\u0010\u0086\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0005\b\u0088\u0001\u0010\u000b\u001a\u0005\b\u0087\u0001\u0010\tR&\u0010\u0089\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0005\b\u008b\u0001\u0010\u000b\u001a\u0005\b\u008a\u0001\u0010\tR&\u0010\u008c\u0001\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\"0\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0005\b\u008e\u0001\u0010\u000b\u001a\u0005\b\u008d\u0001\u0010\tR\'\u0010\u008f\u0001\u001a\u000b\u0012\u0007\u0012\u0005\u0018\u00010\u0090\u00010\u00068FX\u0086\u0084\u0002\u00a2\u0006\u000e\n\u0005\b\u0092\u0001\u0010\u000b\u001a\u0005\b\u0091\u0001\u0010\t\u00a8\u0006\u008a\u0002"}, d2 = {"Lcom/shuttleclone/userapp/ViewModel/HomeFragmentViewModel;", "Landroidx/lifecycle/ViewModel;", "()V", "TAG", "", "addOfficeRideAddress", "Landroidx/lifecycle/MutableLiveData;", "Lcom/shuttleclone/userapp/model/UserProfileUpdateResponse;", "getAddOfficeRideAddress", "()Landroidx/lifecycle/MutableLiveData;", "addOfficeRideAddress$delegate", "Lkotlin/Lazy;", "apiClient", "Lcom/shuttleclone/userapp/retrofitRepository/ApiCalls;", "getApiClient", "()Lcom/shuttleclone/userapp/retrofitRepository/ApiCalls;", "apiClient$delegate", "appNotifications", "Lcom/shuttleclone/userapp/model/NotificationDataResponse;", "getAppNotifications", "appNotifications$delegate", "bookingList", "Lcom/shuttleclone/userapp/model/BookingListResponseModel;", "getBookingList", "bookingList$delegate", "bookingResponse", "Lcom/shuttleclone/userapp/model/BookingResponseModel;", "getBookingResponse", "bookingResponse$delegate", "busSeatsResponse", "Lcom/shuttleclone/userapp/model/BusSeatsResponseModel;", "getBusSeatsResponse", "busSeatsResponse$delegate", "cancelBooking", "Lcom/shuttleclone/userapp/model/DefaultResponse;", "getCancelBooking", "cancelBooking$delegate", "checkWalletBalance", "Lcom/shuttleclone/userapp/model/WalletBalanceResponseModel;", "getCheckWalletBalance", "checkWalletBalance$delegate", "clearNotification", "getClearNotification", "clearNotification$delegate", "exploreRoutes", "Lcom/shuttleclone/userapp/model/ExploreRoutesResponseModel;", "getExploreRoutes", "exploreRoutes$delegate", "fetchNearestStops", "", "Lcom/shuttleclone/userapp/model/SearchedDataItem;", "getFetchNearestStops", "fetchNearestStops$delegate", "getBookingHistory", "Lcom/shuttleclone/userapp/model/BookingTransactionHistoryResponse;", "getGetBookingHistory", "getBookingHistory$delegate", "getConfigSettings", "Lcom/shuttleclone/userapp/model/CommonDataResponse;", "getGetConfigSettings", "getConfigSettings$delegate", "getReferDetails", "Lcom/shuttleclone/userapp/model/ReferCodeModel;", "getGetReferDetails", "getReferDetails$delegate", "getRouteFare", "Lcom/shuttleclone/userapp/model/GeneratedFareResponseModel;", "getGetRouteFare", "getRouteFare$delegate", "getUserProfileDetails", "getGetUserProfileDetails", "getUserProfileDetails$delegate", "getWalletHistory", "Lcom/shuttleclone/userapp/model/WalletHistoryResponseModel;", "getGetWalletHistory", "getWalletHistory$delegate", "helpAndSupport", "getHelpAndSupport", "helpAndSupport$delegate", "initiatePassPayment", "Lcom/shuttleclone/userapp/model/InitiatePaymentResponseModel;", "getInitiatePassPayment", "initiatePassPayment$delegate", "initiatePayment", "getInitiatePayment", "initiatePayment$delegate", "initiateTripPayment", "getInitiateTripPayment", "initiateTripPayment$delegate", "initiateWalletRecharge", "getInitiateWalletRecharge", "initiateWalletRecharge$delegate", "isDataSaved", "", "isDataSaved$delegate", "logOutUser", "getLogOutUser", "logOutUser$delegate", "offers", "Lcom/shuttleclone/userapp/model/OffersResponseModel;", "getOffers", "offers$delegate", "paymentResponse", "Lcom/shuttleclone/userapp/model/PaymentInitiateDataResponse;", "getPaymentResponse", "paymentResponse$delegate", "registerUser", "Lcom/shuttleclone/userapp/model/UserRegisterResponseModel;", "getRegisterUser", "registerUser$delegate", "routeStops", "Lcom/shuttleclone/userapp/model/RouteStopsResponseModel;", "getRouteStops", "routeStops$delegate", "savedSearchLocationList", "getSavedSearchLocationList", "savedSearchLocationList$delegate", "searchLocationList", "getSearchLocationList", "searchLocationList$delegate", "searchedRoutes", "Lcom/shuttleclone/userapp/model/SearchRoutesResponseModel;", "getSearchedRoutes", "searchedRoutes$delegate", "setReminder", "getSetReminder", "setReminder$delegate", "suggestRoutes", "getSuggestRoutes", "suggestRoutes$delegate", "trackRoutes", "Lcom/shuttleclone/userapp/model/TripTrackingStatusResponse;", "getTrackRoutes", "trackRoutes$delegate", "updateLanguage", "getUpdateLanguage", "updateLanguage$delegate", "updateUserProfile", "getUpdateUserProfile", "updateUserProfile$delegate", "validatePhone", "getValidatePhone", "validatePhone$delegate", "verifyUser", "Lcom/shuttleclone/userapp/model/UserVerificationResponseModel;", "getVerifyUser", "verifyUser$delegate", "", "mContext", "Landroid/content/Context;", "token", "home_lat", "home_lng", "home_address", "home_timing", "office_lat", "office_lng", "office_address", "office_timing", "busSeats", "bus_id", "route_id", "routeTimetableId", "pickup_stop_id", "drop_stop_id", "type", "has_return", "currentDate", "endDate", "cancelBookings", "pnr_no", "checkToken", "apiCallBack", "Lcom/shuttleclone/userapp/retrofitRepository/ApiCallBack;", "clearNotifications", "createBooking", "bookingData", "Lcom/shuttleclone/userapp/model/BookingRequestData;", "lat", "lng", "fetchNotification", "perPage", "", "page", "(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Integer;)V", "offSet", "limit", "getBookings", "offset", "travelStatus", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)V", "getProfileDetails", "seat_no", "start_date", "getSavedSearchLocation", "location", "helpSupport", "contact", "helpemail", "description", "payment_name", "pass_id", "pass_no_of_rides", "pass_amount", "payment_mode", "bookingDate", "amount", "", "csrfToken", "onCleared", "payRouteFee", "countyCode", "phoneNo", "deviceId", "countryDetails", "id", "saveSearchLocation", "locationList", "Lcom/shuttleclone/userapp/model/SaveLocationRequestModel;", "searchLocation", "searchRoutes", "pickUpLat", "pickUpLng", "dropLat", "dropLng", "currentTime", "pickUpId", "dropUpId", "every", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "time", "suggestRoute", "officeAddress", "officeLat", "officeLng", "homeAddress", "homeLat", "homeLng", "pickupCity", "pickupState", "dropCity", "dropState", "language", "body", "Lokhttp3/MultipartBody$Part;", "fName", "Lokhttp3/RequestBody;", "lName", "gender", "email", "referedby", "socialid", "mode", "deviceToken", "homeAdd", "officeAdd", "homeLeaveTime", "officeLeaveTime", "validatePhoneNO", "phone", "countryCode", "deviceType", "otp", "mobileVerified", "deviceDetails", "app_debug"})
public final class HomeFragmentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final java.lang.String TAG = "HomeFragmentViewModel";
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy searchLocationList$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy savedSearchLocationList$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy searchedRoutes$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy routeStops$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy trackRoutes$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy busSeatsResponse$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy exploreRoutes$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy getRouteFare$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy bookingResponse$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy paymentResponse$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy initiatePayment$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy initiateTripPayment$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy initiatePassPayment$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy bookingList$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy addOfficeRideAddress$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy updateUserProfile$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy getUserProfileDetails$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy setReminder$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy cancelBooking$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy isDataSaved$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy offers$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy clearNotification$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy appNotifications$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy checkWalletBalance$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy initiateWalletRecharge$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy getBookingHistory$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy logOutUser$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy getConfigSettings$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy helpAndSupport$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy getReferDetails$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy suggestRoutes$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy getWalletHistory$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy registerUser$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy verifyUser$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy validatePhone$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy updateLanguage$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy fetchNearestStops$delegate = null;
    @org.jetbrains.annotations.NotNull
    private final kotlin.Lazy apiClient$delegate = null;
    
    public HomeFragmentViewModel() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.shuttleclone.userapp.model.SearchedDataItem>> getSearchLocationList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.shuttleclone.userapp.model.SearchedDataItem>> getSavedSearchLocationList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.SearchRoutesResponseModel> getSearchedRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.RouteStopsResponseModel> getRouteStops() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.TripTrackingStatusResponse> getTrackRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.BusSeatsResponseModel> getBusSeatsResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.ExploreRoutesResponseModel> getExploreRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.GeneratedFareResponseModel> getGetRouteFare() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.BookingResponseModel> getBookingResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.PaymentInitiateDataResponse> getPaymentResponse() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> getInitiatePayment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> getInitiateTripPayment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> getInitiatePassPayment() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.BookingListResponseModel> getBookingList() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.UserProfileUpdateResponse> getAddOfficeRideAddress() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.UserProfileUpdateResponse> getUpdateUserProfile() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.UserProfileUpdateResponse> getGetUserProfileDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getSetReminder() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getCancelBooking() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.lang.Boolean> isDataSaved() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.OffersResponseModel> getOffers() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getClearNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.NotificationDataResponse> getAppNotifications() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.WalletBalanceResponseModel> getCheckWalletBalance() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> getInitiateWalletRecharge() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.BookingTransactionHistoryResponse> getGetBookingHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getLogOutUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.CommonDataResponse> getGetConfigSettings() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getHelpAndSupport() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.ReferCodeModel> getGetReferDetails() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getSuggestRoutes() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.WalletHistoryResponseModel> getGetWalletHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.UserRegisterResponseModel> getRegisterUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.UserVerificationResponseModel> getVerifyUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getValidatePhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<com.shuttleclone.userapp.model.DefaultResponse> getUpdateLanguage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final androidx.lifecycle.MutableLiveData<java.util.List<com.shuttleclone.userapp.model.SearchedDataItem>> getFetchNearestStops() {
        return null;
    }
    
    private final com.shuttleclone.userapp.retrofitRepository.ApiCalls getApiClient() {
        return null;
    }
    
    public final void searchLocation(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String location, @org.jetbrains.annotations.Nullable
    java.lang.String limit) {
    }
    
    public final void getSavedSearchLocation(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String location, @org.jetbrains.annotations.Nullable
    java.lang.String limit) {
    }
    
    public final void saveSearchLocation(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    com.shuttleclone.userapp.model.SaveLocationRequestModel locationList) {
    }
    
    public final void getOffers(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void searchRoutes(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pickUpLat, @org.jetbrains.annotations.NotNull
    java.lang.String pickUpLng, @org.jetbrains.annotations.NotNull
    java.lang.String dropLat, @org.jetbrains.annotations.NotNull
    java.lang.String dropLng, @org.jetbrains.annotations.NotNull
    java.lang.String currentDate, @org.jetbrains.annotations.NotNull
    java.lang.String currentTime, @org.jetbrains.annotations.NotNull
    java.lang.String endDate, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String pickUpId, @org.jetbrains.annotations.NotNull
    java.lang.String dropUpId, @org.jetbrains.annotations.NotNull
    java.lang.String has_return) {
    }
    
    public final void addOfficeRideAddress(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String home_lat, @org.jetbrains.annotations.NotNull
    java.lang.String home_lng, @org.jetbrains.annotations.NotNull
    java.lang.String home_address, @org.jetbrains.annotations.NotNull
    java.lang.String home_timing, @org.jetbrains.annotations.NotNull
    java.lang.String office_lat, @org.jetbrains.annotations.NotNull
    java.lang.String office_lng, @org.jetbrains.annotations.NotNull
    java.lang.String office_address, @org.jetbrains.annotations.NotNull
    java.lang.String office_timing) {
    }
    
    public final void getProfileDetails(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void updateUserProfile(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    okhttp3.MultipartBody.Part body, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody fName, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody lName, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody gender, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody email, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody referedby, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody socialid, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody mode, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody deviceToken, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody homeAdd, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody homeLat, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody homeLng, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody officeAdd, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody officeLat, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody officeLng, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody homeLeaveTime, @org.jetbrains.annotations.NotNull
    okhttp3.RequestBody officeLeaveTime) {
    }
    
    public final void initiatePassPayment(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String payment_name, @org.jetbrains.annotations.NotNull
    java.lang.String bus_id, @org.jetbrains.annotations.NotNull
    java.lang.String route_id, @org.jetbrains.annotations.NotNull
    java.lang.String routeTimetableId, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String pass_id, @org.jetbrains.annotations.NotNull
    java.lang.String pass_no_of_rides, @org.jetbrains.annotations.NotNull
    java.lang.String pass_amount, @org.jetbrains.annotations.NotNull
    java.lang.String seat_no, @org.jetbrains.annotations.NotNull
    java.lang.String has_return, @org.jetbrains.annotations.NotNull
    java.lang.String payment_mode, @org.jetbrains.annotations.NotNull
    java.lang.String bookingDate) {
    }
    
    public final void routeStops(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id) {
    }
    
    public final void trackRoutes(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no) {
    }
    
    public final void busSeats(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String bus_id, @org.jetbrains.annotations.NotNull
    java.lang.String route_id, @org.jetbrains.annotations.NotNull
    java.lang.String routeTimetableId, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String has_return, @org.jetbrains.annotations.NotNull
    java.lang.String currentDate, @org.jetbrains.annotations.NotNull
    java.lang.String endDate) {
    }
    
    public final void getRouteFare(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String bus_id, @org.jetbrains.annotations.NotNull
    java.lang.String route_id, @org.jetbrains.annotations.NotNull
    java.lang.String routeTimetableId, @org.jetbrains.annotations.NotNull
    java.lang.String pickup_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String drop_stop_id, @org.jetbrains.annotations.NotNull
    java.lang.String seat_no, @org.jetbrains.annotations.NotNull
    java.lang.String start_date, @org.jetbrains.annotations.NotNull
    java.lang.String has_return) {
    }
    
    public final void createBooking(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    com.shuttleclone.userapp.model.BookingRequestData bookingData) {
    }
    
    public final void payRouteFee(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no, @org.jetbrains.annotations.NotNull
    java.lang.String amount, @org.jetbrains.annotations.NotNull
    java.lang.String payment_mode, @org.jetbrains.annotations.NotNull
    java.lang.String bookingDate) {
    }
    
    public final void initiateWalletRecharge(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String payment_name, float amount) {
    }
    
    public final void getBookingHistory(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, int offSet, int limit) {
    }
    
    public final void initiateTripPayment(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String type, @org.jetbrains.annotations.NotNull
    java.lang.String payment_name, float amount, @org.jetbrains.annotations.NotNull
    java.lang.String payment_mode, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no) {
    }
    
    public final void setReminder(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no, @org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> every, @org.jetbrains.annotations.NotNull
    java.lang.String time) {
    }
    
    public final void exploreRoutes(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void getBookings(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.Integer offset, @org.jetbrains.annotations.Nullable
    java.lang.Integer limit, @org.jetbrains.annotations.Nullable
    java.lang.String travelStatus) {
    }
    
    public final void cancelBookings(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String pnr_no, @org.jetbrains.annotations.Nullable
    java.lang.String currentDate) {
    }
    
    public final void clearNotifications(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void fetchNotification(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, int perPage, @org.jetbrains.annotations.Nullable
    java.lang.Integer page) {
    }
    
    public final void checkWalletBalance(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void getConfigSettings(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token) {
    }
    
    public final void getReferDetails(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token) {
    }
    
    public final void fetchNearestStops(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String lat, @org.jetbrains.annotations.NotNull
    java.lang.String lng) {
    }
    
    public final void helpSupport(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String contact, @org.jetbrains.annotations.NotNull
    java.lang.String helpemail, @org.jetbrains.annotations.NotNull
    java.lang.String description) {
    }
    
    public final void suggestRoute(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String officeAddress, @org.jetbrains.annotations.NotNull
    java.lang.String officeLat, @org.jetbrains.annotations.NotNull
    java.lang.String officeLng, @org.jetbrains.annotations.NotNull
    java.lang.String homeAddress, @org.jetbrains.annotations.NotNull
    java.lang.String homeLat, @org.jetbrains.annotations.NotNull
    java.lang.String homeLng, @org.jetbrains.annotations.NotNull
    java.lang.String pickupCity, @org.jetbrains.annotations.NotNull
    java.lang.String pickupState, @org.jetbrains.annotations.NotNull
    java.lang.String dropCity, @org.jetbrains.annotations.NotNull
    java.lang.String dropState) {
    }
    
    public final void getWalletHistory(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token) {
    }
    
    public final void registerUser(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String countyCode, @org.jetbrains.annotations.NotNull
    java.lang.String phoneNo, @org.jetbrains.annotations.NotNull
    java.lang.String deviceId, @org.jetbrains.annotations.NotNull
    java.lang.String countryDetails) {
    }
    
    public final void verifyUser(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String deviceToken, int deviceType, int otp, boolean mobileVerified, @org.jetbrains.annotations.NotNull
    java.lang.String deviceDetails) {
    }
    
    public final void logOutUser(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String token, @org.jetbrains.annotations.Nullable
    java.lang.String csrfToken) {
    }
    
    public final void updateLanguage(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    java.lang.String token, @org.jetbrains.annotations.NotNull
    java.lang.String language) {
    }
    
    public final void validatePhoneNO(@org.jetbrains.annotations.Nullable
    android.content.Context mContext, @org.jetbrains.annotations.Nullable
    java.lang.String phone, @org.jetbrains.annotations.Nullable
    java.lang.String countryCode) {
    }
    
    @java.lang.Override
    protected void onCleared() {
    }
    
    public final void checkToken(@org.jetbrains.annotations.NotNull
    android.content.Context mContext, @org.jetbrains.annotations.NotNull
    com.shuttleclone.userapp.retrofitRepository.ApiCallBack apiCallBack) {
    }
}