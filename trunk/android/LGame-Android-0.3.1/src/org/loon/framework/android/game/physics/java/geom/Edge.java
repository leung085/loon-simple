/*
 * Copyright 1998-2006 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Sun designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Sun in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */
package org.loon.framework.android.game.physics.java.geom;

final class Edge {
    static final int INIT_PARTS = 4;
    static final int GROW_PARTS = 10;

    Curve curve;
    int ctag;
    int etag;
    double activey;
    int equivalence;

    public Edge(Curve c, int ctag) {
        this(c, ctag, AreaOp.ETAG_IGNORE);
    }

    public Edge(Curve c, int ctag, int etag) {
        this.curve = c;
        this.ctag = ctag;
        this.etag = etag;
    }

    public Curve getCurve() {
        return curve;
    }

    public int getCurveTag() {
        return ctag;
    }

    public int getEdgeTag() {
        return etag;
    }

    public void setEdgeTag(int etag) {
        this.etag = etag;
    }

    public int getEquivalence() {
        return equivalence;
    }

    public void setEquivalence(int eq) {
        equivalence = eq;
    }

    private Edge lastEdge;
    private int lastResult;
    private double lastLimit;

    public int compareTo(Edge other, double yrange[]) {
        if (other == lastEdge && yrange[0] < lastLimit) {
            if (yrange[1] > lastLimit) {
                yrange[1] = lastLimit;
            }
            return lastResult;
        }
        if (this == other.lastEdge && yrange[0] < other.lastLimit) {
            if (yrange[1] > other.lastLimit) {
                yrange[1] = other.lastLimit;
            }
            return 0-other.lastResult;
        }
     
        int ret = curve.compareTo(other.curve, yrange);
        
        lastEdge = other;
        lastLimit = yrange[1];
        lastResult = ret;
        return ret;
    }

    public void record(double yend, int etag) {
        this.activey = yend;
        this.etag = etag;
    }

    public boolean isActiveFor(double y, int etag) {
        return (this.etag == etag && this.activey >= y);
    }

}

