package com.sun.xml.bind.v2.runtime.output;

import java.io.IOException;

import javax.xml.stream.XMLStreamException;

import com.sun.xml.bind.v2.runtime.XMLSerializer;
import com.sun.xml.bind.v2.runtime.Name;

import org.xml.sax.SAXException;

/**
 * {@link XmlOutput} that writes to two {@link XmlOutput}s.
 * @author Kohsuke Kawaguchi
 */
public final class ForkXmlOutput extends XmlOutput {
    private final XmlOutput lhs;
    private final XmlOutput rhs;

    public ForkXmlOutput(XmlOutput lhs, XmlOutput rhs) {
        this.lhs = lhs;
        this.rhs = rhs;
    }

    @Override
    public void startDocument(XMLSerializer serializer, boolean fragment, int[] nsUriIndex2prefixIndex, NamespaceContextImpl nsContext) throws IOException, SAXException, XMLStreamException {
        lhs.startDocument(serializer, fragment, nsUriIndex2prefixIndex, nsContext);
        rhs.startDocument(serializer, fragment, nsUriIndex2prefixIndex, nsContext);
    }

    @Override
    public void endDocument(boolean fragment) throws IOException, SAXException, XMLStreamException {
        lhs.endDocument(fragment);
        rhs.endDocument(fragment);
    }

    @Override
    public void beginStartTag(Name name) throws IOException, XMLStreamException {
        lhs.beginStartTag(name);
        rhs.beginStartTag(name);
    }

    @Override
    public void attribute(Name name, String value) throws IOException, XMLStreamException {
        lhs.attribute(name, value);
        rhs.attribute(name, value);
    }

    @Override
    public void attribute(Name name, char[] buf, int len, boolean needEscape) throws IOException, XMLStreamException {
        lhs.attribute(name, buf, len, needEscape);
        rhs.attribute(name, buf, len, needEscape);
    }

    @Override
    public void endTag(Name name) throws IOException, SAXException, XMLStreamException {
        lhs.endTag(name);
        rhs.endTag(name);
    }

    @Override
    public void text(int value) throws IOException, SAXException, XMLStreamException {
        lhs.text(value);
        rhs.text(value);
    }

    @Override
    public void flush() throws IOException, XMLStreamException {
        lhs.flush();
        rhs.flush();
    }

    public void beginStartTag(int prefix, String localName) throws IOException, XMLStreamException {
        lhs.beginStartTag(prefix,localName);
        rhs.beginStartTag(prefix,localName);
    }

    public void attribute(int prefix, String localName, String value) throws IOException, XMLStreamException {
        lhs.attribute(prefix,localName,value);
        rhs.attribute(prefix,localName,value);
    }

    public void endStartTag() throws IOException, SAXException {
        lhs.endStartTag();
        rhs.endStartTag();
    }

    public void endTag(int prefix, String localName) throws IOException, SAXException, XMLStreamException {
        lhs.endTag(prefix,localName);
        rhs.endTag(prefix,localName);
    }

    public void text(CharSequence value, boolean needsSeparatingWhitespace) throws IOException, SAXException, XMLStreamException {
        lhs.text(value,needsSeparatingWhitespace);
        rhs.text(value,needsSeparatingWhitespace);
    }

    public void text(char[] buf, int len) throws IOException, SAXException, XMLStreamException {
        lhs.text(buf,len);
        rhs.text(buf,len);
    }
}
