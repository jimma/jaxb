package com.sun.xml.bind.v2.runtime;

import java.io.IOException;

import javax.activation.MimeType;
import javax.xml.stream.XMLStreamException;

import com.sun.xml.bind.api.AccessorException;

import org.xml.sax.SAXException;

/**
 * Transducer that signals the runtime that this binary data shall be always inlined.
 *
 * @author Kohsuke Kawaguchi
 */
public class InlineBinaryTransducer<V> extends FilterTransducer<V> {
    public InlineBinaryTransducer(Transducer<V> core) {
        super(core);
    }

    @Override
    public CharSequence print(V o) throws AccessorException {
        XMLSerializer w = XMLSerializer.getInstance();
        boolean old = w.setInlineBinaryFlag(true);
        try {
            return core.print(o);
        } finally {
            w.setInlineBinaryFlag(old);
        }
    }

    @Override
    public void writeText(XMLSerializer w, V o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
        boolean old = w.setInlineBinaryFlag(true);
        try {
            core.writeText(w,o,fieldName);
        } finally {
            w.setInlineBinaryFlag(old);
        }
    }

    @Override
    public void writeLeafElement(XMLSerializer w, Name tagName, V o, String fieldName) throws IOException, SAXException, XMLStreamException, AccessorException {
        boolean old = w.setInlineBinaryFlag(true);
        try {
            core.writeLeafElement(w, tagName, o, fieldName);
        } finally {
            w.setInlineBinaryFlag(old);
        }
    }
}
