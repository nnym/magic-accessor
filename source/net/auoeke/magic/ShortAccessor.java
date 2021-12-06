package net.auoeke.magic;

abstract class ShortAccessor extends MagicAccessor {
    @Override
    public boolean getBoolean(Object object) {
        return this.getShort(object) != 0;
    }

    @Override
    public byte getByte(Object object) {
        return (byte) this.getShort(object);
    }

    @Override
    public char getChar(Object object) {
        return (char) this.getShort(object);
    }

    @Override
    public long getLong(Object object) {
        return this.getShort(object);
    }

    @Override
    public int getInt(Object object) {
        return this.getShort(object);
    }

    @Override
    public float getFloat(Object object) {
        return this.getShort(object);
    }

    @Override
    public double getDouble(Object object) {
        return this.getShort(object);
    }

    @Override
    public <T> T get(Object object) {
        return (T) (Short) this.getShort(object);
    }

    @Override
    public void setBoolean(Object object, boolean value) {
        this.setShort(object, (short) (value ? 1 : 0));
    }

    @Override
    public void setByte(Object object, byte value) {
        this.setShort(object, value);
    }

    @Override
    public void setChar(Object object, char value) {
        this.setShort(object, (short) value);
    }

    @Override
    public void setLong(Object object, long value) {
        this.setShort(object, (short) value);
    }

    @Override
    public void setInt(Object object, int value) {
        this.setShort(object, (short) value);
    }

    @Override
    public void setFloat(Object object, float value) {
        this.setShort(object, (short) value);
    }

    @Override
    public void setDouble(Object object, double value) {
        this.setShort(object, (short) value);
    }

    @Override
    public void set(Object object, Object value) {
        this.setShort(object, (short) value);
    }
}
