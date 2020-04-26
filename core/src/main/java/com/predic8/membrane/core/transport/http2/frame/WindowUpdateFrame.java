package com.predic8.membrane.core.transport.http2.frame;

public class WindowUpdateFrame {
    private final Frame frame;

    public WindowUpdateFrame(Frame frame) {
        this.frame = frame;
    }

    public int getWindowSizeIncrement() {
        return
                (frame.content[0] & 0x7F) << 24 |
                (frame.content[1] & 0xFF) << 16 |
                (frame.content[2] & 0xFF) << 8 |
                (frame.content[3] & 0xFF);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("WindowUpdate {\n");
        sb.append("  windowUpdate = ");
        sb.append(getWindowSizeIncrement());
        sb.append("\n");
        sb.append("}");
        return sb.toString();
    }

    public Frame getFrame() {
        return frame;
    }
}
