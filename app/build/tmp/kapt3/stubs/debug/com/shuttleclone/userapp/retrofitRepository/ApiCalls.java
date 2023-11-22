package com.shuttleclone.userapp.retrofitRepository;

@kotlin.Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J&\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\'Jz\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\n\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000e\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u000f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0010\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0011\u001a\u0004\u0018\u00010\u0006H\'J\u0086\u0001\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001b\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001c\u001a\u0004\u0018\u00010\u0006H\'J2\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010 \u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J&\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010&\u001a\u0004\u0018\u00010\'H\'J\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020)0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J2\u0010*\u001a\b\u0012\u0004\u0012\u00020+0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010,\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010-\u001a\u0004\u0018\u00010\u0006H\'J7\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00100\u001a\u0004\u0018\u0001012\n\b\u0001\u00102\u001a\u0004\u0018\u000101H\'\u00a2\u0006\u0002\u00103JC\u00104\u001a\b\u0012\u0004\u0012\u0002050\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00100\u001a\u0004\u0018\u0001012\n\b\u0001\u00102\u001a\u0004\u0018\u0001012\n\b\u0001\u00106\u001a\u0004\u0018\u00010\u0006H\'\u00a2\u0006\u0002\u00107J\u0018\u00108\u001a\b\u0012\u0004\u0012\u0002090\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u0006H\'J>\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010;\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010<\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010=\u001a\u0004\u0018\u00010\u0006H\'J7\u0010>\u001a\b\u0012\u0004\u0012\u00020?0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010@\u001a\u0004\u0018\u0001012\n\b\u0001\u0010A\u001a\u0004\u0018\u000101H\'\u00a2\u0006\u0002\u00103J\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020C0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010D\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'Jv\u0010G\u001a\b\u0012\u0004\u0012\u00020H0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010I\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010J\u001a\u00020\u00062\b\b\u0001\u0010K\u001a\u00020\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\'J2\u0010L\u001a\b\u0012\u0004\u0012\u00020+0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00102\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010M\u001a\b\u0012\u0004\u0012\u00020N0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J\u00c2\u0001\u0010O\u001a\b\u0012\u0004\u0012\u00020P0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010I\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010S\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010T\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010V\u001a\u0004\u0018\u00010\u0006H\'J[\u0010W\u001a\b\u0012\u0004\u0012\u00020P0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010X2\n\b\u0001\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\'\u00a2\u0006\u0002\u0010YJC\u0010Z\u001a\b\u0012\u0004\u0012\u00020P0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010Q\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010XH\'\u00a2\u0006\u0002\u0010[J&\u0010\\\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010]\u001a\u0004\u0018\u00010\u0006H\'J\u00aa\u0001\u0010^\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010I\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0015\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0016\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010R\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010S\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010T\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010J\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010V\u001a\u0004\u0018\u00010\u0006H\'JJ\u0010_\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010U\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010V\u001a\u0004\u0018\u00010\u0006H\'J\u001a\u0010`\u001a\b\u0012\u0004\u0012\u00020a0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'J2\u0010b\u001a\b\u0012\u0004\u0012\u00020c0\u00032\n\b\u0001\u0010d\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010e\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010f\u001a\u0004\u0018\u00010\u0006H\'JD\u0010g\u001a\b\u0012\u0004\u0012\u00020h0\u00032\n\b\u0001\u0010i\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010d\u001a\u0004\u0018\u00010\u00062\b\b\u0001\u0010j\u001a\u00020\u00062\b\b\u0001\u0010k\u001a\u00020\u00062\b\b\u0001\u0010l\u001a\u00020\u0006H\'J>\u0010m\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010n\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010i\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010k\u001a\u0004\u0018\u00010\u0006H\'J>\u0010o\u001a\b\u0012\u0004\u0012\u00020p0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0017\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0018\u001a\u0004\u0018\u00010\u0006H\'J&\u0010q\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010&\u001a\u0004\u0018\u00010rH\'J2\u0010s\u001a\b\u0012\u0004\u0012\u00020+0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0014\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u00102\u001a\u0004\u0018\u00010\u0006H\'J\u009e\u0001\u0010t\u001a\b\u0012\u0004\u0012\u00020u0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010v\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010w\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010y\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010 \u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010z\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010{\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u0019\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010|\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010}\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001a\u001a\u0004\u0018\u00010\u0006H\'JO\u0010~\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u00062\u001a\b\u0001\u0010\u007f\u001a\u0014\u0012\u0004\u0012\u00020\u00060\u0080\u0001j\t\u0012\u0004\u0012\u00020\u0006`\u0081\u00012\u000b\b\u0001\u0010\u0082\u0001\u001a\u0004\u0018\u00010\u0006H\'J\u009b\u0001\u0010\u0083\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010v\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0085\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0086\u0001\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010x\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0087\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u0089\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u0006H\'J(\u0010\u008c\u0001\u001a\t\u0012\u0005\u0012\u00030\u008d\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\n\b\u0001\u0010\u001f\u001a\u0004\u0018\u00010\u0006H\'J#\u0010\u008e\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\b\b\u0001\u0010\u0005\u001a\u00020\u00062\b\b\u0001\u0010l\u001a\u00020\u0006H\'J\u0081\u0002\u0010\u008f\u0001\u001a\b\u0012\u0004\u0012\u00020\t0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u00012\f\b\u0001\u0010\u0092\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u0095\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u0096\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u0097\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u0098\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0093\u00012\f\b\u0001\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\f\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\n\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\u000b\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\u0010\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\u000e\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\u000f\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\r\u001a\u0005\u0018\u00010\u0093\u00012\u000b\b\u0001\u0010\u0011\u001a\u0005\u0018\u00010\u0093\u0001H\'J(\u0010\u009b\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010d\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0006H\'JV\u0010\u009d\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u00a0\u0001\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u00a1\u0001\u001a\u0005\u0018\u00010\u00a2\u0001H\'\u00a2\u0006\u0003\u0010\u00a3\u0001JV\u0010\u00a4\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u00a0\u0001\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u00a1\u0001\u001a\u0005\u0018\u00010\u00a2\u0001H\'\u00a2\u0006\u0003\u0010\u00a3\u0001JS\u0010\u00a5\u0001\u001a\t\u0012\u0005\u0012\u00030\u00a6\u00010\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\t\b\u0001\u0010\u009a\u0001\u001a\u00020\u00062\t\b\u0001\u0010\u00a7\u0001\u001a\u0002012\b\b\u0001\u0010n\u001a\u0002012\n\b\u0001\u0010\u00a8\u0001\u001a\u00030\u00a2\u00012\t\b\u0001\u0010\u00a9\u0001\u001a\u00020\u0006H\'JV\u0010\u00aa\u0001\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00032\n\b\u0001\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009e\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00062\u000b\b\u0001\u0010\u00a0\u0001\u001a\u0004\u0018\u00010\u00062\f\b\u0001\u0010\u00a1\u0001\u001a\u0005\u0018\u00010\u00a2\u0001H\'\u00a2\u0006\u0003\u0010\u00a3\u0001\u00a8\u0006\u00ab\u0001"}, d2 = {"Lcom/shuttleclone/userapp/retrofitRepository/ApiCalls;", "", "addMoney", "Lio/reactivex/Single;", "Lcom/shuttleclone/userapp/model/PaymentInitiateDataResponse;", "token", "", "amount", "addOfficeRideAddress", "Lcom/shuttleclone/userapp/model/UserProfileUpdateResponse;", "home_lat", "home_lng", "home_address", "home_timing", "office_lat", "office_lng", "office_address", "office_timing", "busSeatsLayout", "Lcom/shuttleclone/userapp/model/BusSeatsResponseModel;", "address", "route_id", "routeTimetableId", "pickup_stop_id", "drop_stop_id", "type", "has_return", "currentDate", "endDate", "cancelBooking", "Lcom/shuttleclone/userapp/model/DefaultResponse;", "pnr_no", "current_date", "checkWalletBalance", "Lcom/shuttleclone/userapp/model/WalletBalanceResponseModel;", "clearNotifications", "createBooking", "Lcom/shuttleclone/userapp/model/BookingResponseModel;", "data", "Lcom/shuttleclone/userapp/model/BookingRequestData;", "exploreRoutes", "Lcom/shuttleclone/userapp/model/ExploreRoutesResponseModel;", "fetchNearestStops", "Lcom/shuttleclone/userapp/model/SearchLocationResponseModel;", "lat", "lng", "getBookingHistory", "Lcom/shuttleclone/userapp/model/BookingTransactionHistoryResponse;", "offset", "", "limit", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;)Lio/reactivex/Single;", "getBookingList", "Lcom/shuttleclone/userapp/model/BookingListResponseModel;", "travelStatus", "(Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;)Lio/reactivex/Single;", "getConfigSettings", "Lcom/shuttleclone/userapp/model/CommonDataResponse;", "getHelp", "contact", "helpemail", "description", "getNotifications", "Lcom/shuttleclone/userapp/model/NotificationDataResponse;", "perPage", "page", "getOffers", "Lcom/shuttleclone/userapp/model/OffersResponseModel;", "getProfileDetails", "getReferDetails", "Lcom/shuttleclone/userapp/model/ReferCodeModel;", "getRouteFare", "Lcom/shuttleclone/userapp/model/GeneratedFareResponseModel;", "bus_id", "seat_no", "start_date", "getSearchLocation", "getWalletHistory", "Lcom/shuttleclone/userapp/model/WalletHistoryResponseModel;", "initiatePassPayment", "Lcom/shuttleclone/userapp/model/InitiatePaymentResponseModel;", "payment_name", "pass_id", "pass_no_of_rides", "pass_amount", "payment_mode", "date", "initiateTripPayment", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;Ljava/lang/String;Ljava/lang/String;)Lio/reactivex/Single;", "initiateWalletRechargePayment", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Float;)Lio/reactivex/Single;", "logOutUser", "ctoken", "payForPass", "payRouteFee", "paymentSettings", "Lcom/shuttleclone/userapp/model/RzPayDataResponseModel;", "refreshToken", "Lcom/shuttleclone/userapp/model/RefreshTokenModel;", "phone", "csrfToken", "onModel", "registerUser", "Lcom/shuttleclone/userapp/model/UserRegisterResponseModel;", "countryCode", "device_id", "countryDetails", "language", "resendOtp", "otp", "routeStops", "Lcom/shuttleclone/userapp/model/RouteStopsResponseModel;", "saveSearchLocationData", "Lcom/shuttleclone/userapp/model/SaveLocationRequestModel;", "searchLocation", "searchRoutes", "Lcom/shuttleclone/userapp/model/SearchRoutesResponseModel;", "pickup_lat", "pickup_long", "drop_lat", "drop_long", "current_time", "end_date", "pickup_id", "drop_id", "setReminder", "every", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "time", "suggestRoute", "pickup_address", "pickup_lng", "drop_address", "drop_lng", "pickup_city", "pickup_state", "drop_city", "drop_state", "tripTracking", "Lcom/shuttleclone/userapp/model/TripTrackingStatusResponse;", "updateLanguage", "updateUser", "file", "Lokhttp3/MultipartBody$Part;", "firstname", "Lokhttp3/RequestBody;", "lastname", "gender", "email", "referedby", "socialid", "mode", "device_token", "validatedPhoneNo", "country_code", "verifyBookingPayment", "paymentId", "orderId", "signature", "status", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lio/reactivex/Single;", "verifyPassPayment", "verifyUser", "Lcom/shuttleclone/userapp/model/UserVerificationResponseModel;", "deviceType", "is_mobile_verified", "device_info", "verifyWalletAddPayment", "app_debug"})
public abstract interface ApiCalls {
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/register")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.UserRegisterResponseModel> registerUser(@retrofit2.http.Field(value = "country_code")
    @org.jetbrains.annotations.Nullable
    java.lang.String countryCode, @retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String phone, @retrofit2.http.Field(value = "device_id")
    @org.jetbrains.annotations.NotNull
    java.lang.String device_id, @retrofit2.http.Field(value = "country_details")
    @org.jetbrains.annotations.NotNull
    java.lang.String countryDetails, @retrofit2.http.Field(value = "language")
    @org.jetbrains.annotations.NotNull
    java.lang.String language);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.UserVerificationResponseModel> verifyUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "device_token")
    @org.jetbrains.annotations.NotNull
    java.lang.String device_token, @retrofit2.http.Field(value = "device_type")
    int deviceType, @retrofit2.http.Field(value = "otp")
    int otp, @retrofit2.http.Field(value = "is_mobile_verified")
    boolean is_mobile_verified, @retrofit2.http.Field(value = "device_info")
    @org.jetbrains.annotations.NotNull
    java.lang.String device_info);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/re-send")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> resendOtp(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String otp, @retrofit2.http.Field(value = "country_code")
    @org.jetbrains.annotations.Nullable
    java.lang.String countryCode, @retrofit2.http.Field(value = "country_details")
    @org.jetbrains.annotations.Nullable
    java.lang.String countryDetails);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/refresh-token")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.RefreshTokenModel> refreshToken(@retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String phone, @retrofit2.http.Field(value = "csrfToken")
    @org.jetbrains.annotations.Nullable
    java.lang.String csrfToken, @retrofit2.http.Field(value = "onModel")
    @org.jetbrains.annotations.Nullable
    java.lang.String onModel);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/help")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> getHelp(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "contact")
    @org.jetbrains.annotations.Nullable
    java.lang.String contact, @retrofit2.http.Field(value = "helpemail")
    @org.jetbrains.annotations.Nullable
    java.lang.String helpemail, @retrofit2.http.Field(value = "description")
    @org.jetbrains.annotations.Nullable
    java.lang.String description);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/payment/verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> verifyWalletAddPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "paymentId")
    @org.jetbrains.annotations.Nullable
    java.lang.String paymentId, @retrofit2.http.Field(value = "orderId")
    @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @retrofit2.http.Field(value = "signature")
    @org.jetbrains.annotations.Nullable
    java.lang.String signature, @retrofit2.http.Field(value = "status")
    @org.jetbrains.annotations.Nullable
    java.lang.Boolean status);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/pass-payment-verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> verifyPassPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "paymentId")
    @org.jetbrains.annotations.Nullable
    java.lang.String paymentId, @retrofit2.http.Field(value = "orderId")
    @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @retrofit2.http.Field(value = "signature")
    @org.jetbrains.annotations.Nullable
    java.lang.String signature, @retrofit2.http.Field(value = "status")
    @org.jetbrains.annotations.Nullable
    java.lang.Boolean status);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/payment-verify")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> verifyBookingPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "paymentId")
    @org.jetbrains.annotations.Nullable
    java.lang.String paymentId, @retrofit2.http.Field(value = "orderId")
    @org.jetbrains.annotations.Nullable
    java.lang.String orderId, @retrofit2.http.Field(value = "signature")
    @org.jetbrains.annotations.Nullable
    java.lang.String signature, @retrofit2.http.Field(value = "status")
    @org.jetbrains.annotations.Nullable
    java.lang.Boolean status);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/addmoney")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.PaymentInitiateDataResponse> addMoney(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String amount);
    
    @retrofit2.http.GET(value = "users/walletcheck")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.WalletBalanceResponseModel> checkWalletBalance(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/trip-track")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.TripTrackingStatusResponse> tripTracking(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/searchlocation")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.SearchLocationResponseModel> searchLocation(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "address")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.String limit);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "searches/google")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.SearchLocationResponseModel> getSearchLocation(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "address")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.String limit);
    
    @retrofit2.http.POST(value = "searches/savelocation")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> saveSearchLocationData(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Body
    @org.jetbrains.annotations.Nullable
    com.shuttleclone.userapp.model.SaveLocationRequestModel data);
    
    @retrofit2.http.GET(value = "routes/explore")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.ExploreRoutesResponseModel> exploreRoutes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "routes/{id}")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.RouteStopsResponseModel> routeStops(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "buses/{id}")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.BusSeatsResponseModel> busSeatsLayout(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Path(value = "id")
    @org.jetbrains.annotations.Nullable
    java.lang.String address, @retrofit2.http.Field(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Field(value = "busschedule_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String routeTimetableId, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Field(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return, @retrofit2.http.Field(value = "current_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String currentDate, @retrofit2.http.Field(value = "end_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String endDate);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "routes/route-search")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.SearchRoutesResponseModel> searchRoutes(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pickup_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_lat, @retrofit2.http.Field(value = "pickup_long")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_long, @retrofit2.http.Field(value = "drop_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_lat, @retrofit2.http.Field(value = "drop_long")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_long, @retrofit2.http.Field(value = "current_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String current_date, @retrofit2.http.Field(value = "current_time")
    @org.jetbrains.annotations.Nullable
    java.lang.String current_time, @retrofit2.http.Field(value = "end_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String end_date, @retrofit2.http.Field(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Field(value = "pickup_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_id, @retrofit2.http.Field(value = "drop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_id, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/add-update-office-and-home")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.UserProfileUpdateResponse> addOfficeRideAddress(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "home_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_lat, @retrofit2.http.Field(value = "home_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_lng, @retrofit2.http.Field(value = "home_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_address, @retrofit2.http.Field(value = "home_timing")
    @org.jetbrains.annotations.Nullable
    java.lang.String home_timing, @retrofit2.http.Field(value = "office_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_lat, @retrofit2.http.Field(value = "office_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_lng, @retrofit2.http.Field(value = "office_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_address, @retrofit2.http.Field(value = "office_timing")
    @org.jetbrains.annotations.Nullable
    java.lang.String office_timing);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/payment-pass")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.PaymentInitiateDataResponse> payForPass(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "bus_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String bus_id, @retrofit2.http.Field(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Field(value = "busschedule_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String routeTimetableId, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Field(value = "pass_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_id, @retrofit2.http.Field(value = "pass_no_of_rides")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_no_of_rides, @retrofit2.http.Field(value = "pass_amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_amount, @retrofit2.http.Field(value = "seat_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String seat_no, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return, @retrofit2.http.Field(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_mode, @retrofit2.http.Field(value = "date")
    @org.jetbrains.annotations.Nullable
    java.lang.String date);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "fare/generate-seat-fare")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.GeneratedFareResponseModel> getRouteFare(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "bus_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String bus_id, @retrofit2.http.Field(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Field(value = "busschedule_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String routeTimetableId, @retrofit2.http.Field(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Field(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Field(value = "seat_no")
    @org.jetbrains.annotations.NotNull
    java.lang.String seat_no, @retrofit2.http.Field(value = "start_date")
    @org.jetbrains.annotations.NotNull
    java.lang.String start_date, @retrofit2.http.Field(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return);
    
    @retrofit2.http.POST(value = "booking/create")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.BookingResponseModel> createBooking(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Body
    @org.jetbrains.annotations.Nullable
    com.shuttleclone.userapp.model.BookingRequestData data);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/my-trips")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.BookingListResponseModel> getBookingList(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "offset")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer offset, @retrofit2.http.Field(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer limit, @retrofit2.http.Field(value = "travel_status")
    @org.jetbrains.annotations.Nullable
    java.lang.String travelStatus);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/payment")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.PaymentInitiateDataResponse> payRouteFee(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no, @retrofit2.http.Field(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String amount, @retrofit2.http.Field(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_mode, @retrofit2.http.Field(value = "date")
    @org.jetbrains.annotations.Nullable
    java.lang.String date);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/setreminder")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> setReminder(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no, @retrofit2.http.Field(value = "every")
    @org.jetbrains.annotations.NotNull
    java.util.ArrayList<java.lang.String> every, @retrofit2.http.Field(value = "time")
    @org.jetbrains.annotations.Nullable
    java.lang.String time);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "suggest/create")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> suggestRoute(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pickup_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_address, @retrofit2.http.Field(value = "pickup_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_lat, @retrofit2.http.Field(value = "pickup_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_lng, @retrofit2.http.Field(value = "drop_address")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_address, @retrofit2.http.Field(value = "drop_lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_lat, @retrofit2.http.Field(value = "drop_lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_lng, @retrofit2.http.Field(value = "pickup_city")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_city, @retrofit2.http.Field(value = "pickup_state")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_state, @retrofit2.http.Field(value = "drop_city")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_city, @retrofit2.http.Field(value = "drop_state")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_state);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "booking/cancel")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> cancelBooking(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no, @retrofit2.http.Field(value = "current_date")
    @org.jetbrains.annotations.Nullable
    java.lang.String current_date);
    
    @retrofit2.http.GET(value = "offers")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.OffersResponseModel> getOffers(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/wallet-transactions")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.WalletHistoryResponseModel> getWalletHistory(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/booking-transactions")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.BookingTransactionHistoryResponse> getBookingHistory(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Query(value = "offset")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer offset, @retrofit2.http.Query(value = "limit")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer limit);
    
    @retrofit2.http.PUT(value = "users/logout")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> logOutUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Header(value = "csrf-token")
    @org.jetbrains.annotations.Nullable
    java.lang.String ctoken);
    
    @retrofit2.http.GET(value = "users/me")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.UserProfileUpdateResponse> getProfileDetails(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/refercode")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.ReferCodeModel> getReferDetails(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "settings?type=payments")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.RzPayDataResponseModel> paymentSettings(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.GET(value = "payments/pay")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> initiateWalletRechargePayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Query(value = "payment_name")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_name, @retrofit2.http.Query(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.Float amount);
    
    @retrofit2.http.GET(value = "payments/pay")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> initiateTripPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Query(value = "payment_name")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_name, @retrofit2.http.Query(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.Float amount, @retrofit2.http.Query(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_mode, @retrofit2.http.Query(value = "pnr_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String pnr_no);
    
    @retrofit2.http.GET(value = "payments/pay")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.InitiatePaymentResponseModel> initiatePassPayment(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Query(value = "type")
    @org.jetbrains.annotations.Nullable
    java.lang.String type, @retrofit2.http.Query(value = "payment_name")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_name, @retrofit2.http.Query(value = "bus_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String bus_id, @retrofit2.http.Query(value = "route_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String route_id, @retrofit2.http.Query(value = "busschedule_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String routeTimetableId, @retrofit2.http.Query(value = "pickup_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pickup_stop_id, @retrofit2.http.Query(value = "drop_stop_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String drop_stop_id, @retrofit2.http.Query(value = "pass_id")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_id, @retrofit2.http.Query(value = "pass_no_of_rides")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_no_of_rides, @retrofit2.http.Query(value = "amount")
    @org.jetbrains.annotations.Nullable
    java.lang.String pass_amount, @retrofit2.http.Query(value = "seat_no")
    @org.jetbrains.annotations.Nullable
    java.lang.String seat_no, @retrofit2.http.Query(value = "has_return")
    @org.jetbrains.annotations.Nullable
    java.lang.String has_return, @retrofit2.http.Query(value = "payment_mode")
    @org.jetbrains.annotations.Nullable
    java.lang.String payment_mode, @retrofit2.http.Query(value = "date")
    @org.jetbrains.annotations.Nullable
    java.lang.String date);
    
    @retrofit2.http.Multipart
    @retrofit2.http.POST(value = "users/updateuser")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.UserProfileUpdateResponse> updateUser(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Part
    @org.jetbrains.annotations.Nullable
    okhttp3.MultipartBody.Part file, @retrofit2.http.Part(value = "firstname")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody firstname, @retrofit2.http.Part(value = "lastname")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody lastname, @retrofit2.http.Part(value = "gender")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody gender, @retrofit2.http.Part(value = "email")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody email, @retrofit2.http.Part(value = "referedby")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody referedby, @retrofit2.http.Part(value = "social_id")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody socialid, @retrofit2.http.Part(value = "mode")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody mode, @retrofit2.http.Part(value = "device_token")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody device_token, @retrofit2.http.Part(value = "home_address")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_address, @retrofit2.http.Part(value = "home_lat")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_lat, @retrofit2.http.Part(value = "home_lng")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_lng, @retrofit2.http.Part(value = "office_address")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_address, @retrofit2.http.Part(value = "office_lat")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_lat, @retrofit2.http.Part(value = "office_lng")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_lng, @retrofit2.http.Part(value = "home_timing")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody home_timing, @retrofit2.http.Part(value = "office_timing")
    @org.jetbrains.annotations.Nullable
    okhttp3.RequestBody office_timing);
    
    @retrofit2.http.GET(value = "settings/commondata")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.CommonDataResponse> getConfigSettings(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String token);
    
    @retrofit2.http.GET(value = "users/notification/lists")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.NotificationDataResponse> getNotifications(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Query(value = "perPage")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer perPage, @retrofit2.http.Query(value = "page")
    @org.jetbrains.annotations.Nullable
    java.lang.Integer page);
    
    @retrofit2.http.GET(value = "users/notification/clear-all")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> clearNotifications(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/validate-mobile-number")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> validatedPhoneNo(@retrofit2.http.Field(value = "phone")
    @org.jetbrains.annotations.Nullable
    java.lang.String phone, @retrofit2.http.Field(value = "country_code")
    @org.jetbrains.annotations.Nullable
    java.lang.String country_code);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "routes/nearest-stops")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.SearchLocationResponseModel> fetchNearestStops(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.Nullable
    java.lang.String token, @retrofit2.http.Field(value = "lat")
    @org.jetbrains.annotations.Nullable
    java.lang.String lat, @retrofit2.http.Field(value = "lng")
    @org.jetbrains.annotations.Nullable
    java.lang.String lng);
    
    @retrofit2.http.FormUrlEncoded
    @retrofit2.http.POST(value = "users/update-language")
    @org.jetbrains.annotations.NotNull
    public abstract io.reactivex.Single<com.shuttleclone.userapp.model.DefaultResponse> updateLanguage(@retrofit2.http.Header(value = "Authorization")
    @org.jetbrains.annotations.NotNull
    java.lang.String token, @retrofit2.http.Field(value = "language")
    @org.jetbrains.annotations.NotNull
    java.lang.String language);
}