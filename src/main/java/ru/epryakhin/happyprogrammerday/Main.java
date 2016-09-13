package ru.epryakhin.happyprogrammerday;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;
import sun.misc.BASE64Decoder;

/**
 *
 * @author epryakhin
 */
public class Main {

    public static void main(String[] args) throws Exception {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        String something = null;
        try (GZIPInputStream stream = new GZIPInputStream(new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(s)))) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int b;
            while ((b = stream.read()) != -1) {
                baos.write(b);
            }
            something = new String(baos.toByteArray());
        }
        JavaFileObject file = new JavaSourceFromString("MeaningOfLive", something);
        Iterable<? extends JavaFileObject> compilationUnits = Arrays.asList(file);
        CompilationTask task = compiler.getTask(null, null, diagnostics, null, null, compilationUnits);
        task.call();
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{new File("").toURI().toURL()});
        Class c = Class.forName("MeaningOfLive", true, classLoader);
        Object o = c.getConstructor().newInstance();
        o.getClass().getDeclaredMethod("calculate").invoke(o);
    }

    private static class JavaSourceFromString extends SimpleJavaFileObject {

        final String code;

        JavaSourceFromString(String name, String code) {
            super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }

    private static final String s
            = "H4sIAAAAAAAAAN1Za3vithL+nl+h5ctCN48Xkk130+z2FEgg5pYEEiD09INsC+NgW15Lhjht/vsZ\n"
            + "+QI2CJJue872OX7CTTOayzuj0UixHI/6HD3gBVYsqtSC6ZT4xOgTbBD/7MDKkxuWTbYG1auLR514\n"
            + "3KLuNs31Aj7gPsGOTKRLuHLXV2WDHelg3caMdei2pIBbtlL1fRwyCaFObZvo2xZGRJUTH3OaF/io\n"
            + "cEptppxb2HQp45aeyNjB14Lvdep4ANDLDEr8BQuDbjGb75wg8L7SHkCtlGUAYzZ5BeMtvF/7dGFl\n"
            + "cWOBqzgW05VadXDx44dzotOIfPD+hx8OkPhDv+CAz6iPiOeHeD6zXBh8f+AFmm3pSBexQF2CXcs1\n"
            + "r6Yda0HQ7wcHCJ6EY0EtA+nY1gPwlRRLiM98umRolS/Aj5IHgLapyZCRfH5BLlmmo8XS2YoxYVA8\n"
            + "33K56nKfZqkwQEziI2Y9ESEjsO01kfshKuZTHPmJpvxwUQxtJW9xEDJOHMVyS6VSxnbxAB/AgGzL\n"
            + "JWc5wnIGsUFF7gdkc454xAQwwVdAjdGBH1lv0seaoqJgVECJUywpFrtwPB4Wt6xIn8T9Snlblng0\n"
            + "UDbfJj0jYjOyQ6QAT07JKEzgVzzsMwI/IqslDmUdi6Z+Rifojz9iMT+jo5Ndjq2sEZkUBa4XOBrx\n"
            + "G9R3MF8llgzEV3mZPjsQWsmQUp4h2bk+Q0WpTcidSjMgfTYSG3KvazHmCIm73Nk2Iz+y/vUsXz9Q\n"
            + "FEyfsN0LbIQ5o26fMI+6jOxkGwTMs3RwcrckUaayRBG7a0GAdVWKWYrLGebn9J4GXULcKDFKu8SB\n"
            + "SYHNU4HPSd3xrQVUGrSu95/jdfkzyotOy4RjucYAKhTZW5uytRvp6ZcvKFtWFZPwuDxkubMOS7aS\n"
            + "z/na/bNwMeHJVMCXZmWV5EloKmpPLEhQBjTwddLwqRPDUizk41s4jFgTYlQWs3sDxMmIvsXFsrjM\n"
            + "TS5lYxVtqppNPv8LkUdOXIOhTV/19SZ451pcOBxv4ApmHYvxojA+I3Jj00RcvH1ZxUMEQIwXRc0/\n"
            + "RPF7Bs90KH7fVJ7RI+QqkBN2Ftd87xHvfcn3LxtE6FWWqss4dnUSYQjkX3/7XXwT7hcLhRJsy9D2\n"
            + "FOPPTrH0nHUz2lZ1EBt9U6bU72GHSEIltpTDrCkZKUn8qQBIIFOHOcAv0idNfiWaWcqZu14Pa1E+\n"
            + "4YHvouL2oiohGskWcsAZ+AqJYmPYRbsE+gajWHDwnIC7lrugc1Kk+dUaNxCyvFzljKzDSZsM8Uwt\n"
            + "F9vp3isy8+wgtxS2Ej5hdQHRw+y8zcLMAg8SHIKk6LATQPNSYBHzT+/fvy+gd5EA2LI9GwNqb5W3\n"
            + "h+jt+7clILQBQWVwddevXyiRFwwAKx1mxzeKOZ9ZTBE2RMlsZNqH57Uzv1wtiO9DoVmNJD1WfYb9\n"
            + "AfkaEAgfEsGA3xBsUMyLGtQnaM+QBWvAJxcuSAcXLnyf+mzT4STKmwZkzEiLaxy2tF/LBCPb9WW7\n"
            + "s7S0irzz/cDjxJBVWfFkN4XKSbmcbgz5wiDdC9eFCxZb4RJ7XghWwPaGHYf40FjiEFXVdOgNrJ+C\n"
            + "eA0WhONDBFmGoSkgQjNi1CFQJ8QejqaQO9CbcApNCfq1Uv7tJ1TIb7KleCWWpHGTYJLf2L8HOLAL\n"
            + "+ql/FkMu5cik1FDQLfR42MSWK5C5zkIS0gBN8YL6Fv+vQbNuRr4DKDvbMqi7Fk/yQ1GUwuFuTlh3\n"
            + "rqiQUFOalJqw9TLiLyydsGgiKly1XzldrXVRXO9fNzM5Y4mpfEaQEx/KEJ3C+WLxktnK+vkb2DJw\n"
            + "RaUtzTBGobsX1fx/as0r2c6pS97EeSRw86PmUsx5RdtdemV6b7bS/6gk3wfOHlq+A/wbwcoeKPYB\n"
            + "dYgy1097UDv6J6FWuHQcB7IL2ZTSOYM1OifxpuNTDZbKn16+f4HcIdB5i7M9lPHAhSZSdIJGvHKh\n"
            + "5cGuScRyiOL7Zp+gWzHDXvuDkQYl4McPiEMb9AIYL9v4dmVkfAJBFt9rzTfS/srp59tjINLhcFUs\n"
            + "Rd8a9WF7HXxB5G0Mlh+4LyL1N67a+Iz/HQrbt5mb3iH8P1TivbS4yd3HoVMX2mIexEdiOCw7ISzm\n"
            + "BbHDfbNEpdAI42hJHezu5bRtBOL8qD1ZUt829nEvLT4TLacv2vUptIRgE5zzoIV/843pKj3DJIHM\n"
            + "nWEShvhgCWSoNzYOz7ayaHV1tZEgYhUXK5AY+1IwnbySL5OiRBQ4FCYW7Ba3zmjJIR2xP5PfsRSI\n"
            + "/xTb7LWNu1TrIUqPny7t2VduBzP+ZyxJ/y2zkmjx6LKFKVZC2bwVTW7aBVmZYdaDfUd+Q567NWAV\n"
            + "EBrNcaMJ2zetU+qjKFQWcJbP4OMzzFJs4ppcXM0i6927XRe7yX8MaMCTQgETdTiiV3nRKkl0bd/o\n"
            + "iivyN2sIxS35yx7KVNvSW/Fthbcz8a8IhdmEeMU4O/PTNm+V4/ckK6B94PCRgJvfIwG+Ah5Vltpx\n"
            + "q6w2vZnhXAR49Cm4cYfOxBmGk1F/MBk15pOx+rHu2FxvnoZGvfb1fjyZdRx70ZlPPK15t6L9281X\n"
            + "g3eokGe3r/vj2cNkXCvj0WlwtfQ8bVxb6O6NiZ3G0f3gxNPCk5bm1ipGo1fWneFMG7bs+1Hf1h9Y\n"
            + "WyJeZv3dcT/EoxN3eNzyjOYwlKgJJuObYDhstbI0mfg8e3ePqO7N0eNMP+51tSM5YBLxHXcIQCyD\n"
            + "m3ErvB/bXTzulSUqKkbTZp15I9Sdxon+xNqbXhvjvicRr9VPzrWjRzYZ9XbhnYi2y5Nxa2aAtbt4\n"
            + "ZNbPHz39+Oajfjm0tKb9oDZ77H7ce1Ibxsy47C00u2Xrx7WF5vZs9ZK161bVVOvxS7+E2DiN8mRQ\n"
            + "cyBWMnC0eg0yoW9Pjoah2vzwsW7S1XT1sla5dx69+7A2vh/3n7Sjk8Fk3FvC59NkYHpC3YoXXhLx\n"
            + "Ythozjw9NB97g/nHrHXTG5qZXlsaoxbDo645PGqU9aPT4M4ZPkFoA/3ojg5GJ2WJ+MnIsHWrFrQH\n"
            + "tZzo2Po+xeNuoFnV08izF1RL875r3hydMq05fIDwLLSHx1sDMNUc/Z3arMwwWAaqy/iytTCOu+bg\n"
            + "7rRBmj1bv+x72tEHkyzpS+CA9ig0EIcoMa+jFWkHk6cPJqzMJ2P0WFbPy6bmDI/Vizg5b5v2k3G+\n"
            + "fNeurwHdKR5ezclCt6oU1EAuz83rQZV3w+pXAMXshNXwKqx56vkyAgnofjuc5yx/QTwAcqLWZwkw\n"
            + "xhishzxXTSMpDCvrU88qRojHUGss00vjts/6jdfEOQ1BnaeJpSNCWy8/qXVqQpgX6rkKHlDzGlRm\n"
            + "PZSIB6e/tkO2lcOyFx7dm+36rAdrgHachqWHM7C86qsXFVjNZiARfz9qPbWbVOAKls1fVJFJxk/T\n"
            + "epW265VArX8yu1aVy8Bpzj3hdJYtHmNmO3EagAGA5mYbQp+AY3YG4PWg+ul6UAtS0GTiz1Vvaznt\n"
            + "eU0vl2CKyTNqBEBfVRiT1Zw8m6/mp35Np+6zWpgoskci/nWW146TRA3iRL2jqgU5b+Vr1GsT87Js\n"
            + "TkaPUBUldWiPellJy1n0YcuiHeqlGba75pzuE5nWHcj1+aQ+WwPVPE3LH5S9+euxl6iTWAxqy8a4\n"
            + "FaiXYkcbygqycZ5dranY2mnh7OD54D9+HWkL1SUAAA==";
}
