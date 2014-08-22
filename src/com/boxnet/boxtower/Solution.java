package com.boxnet.boxtower;

import java.util.Vector;
import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution bt = new Solution();
		
		bt.addBox(5, 2, 4);
		bt.addBox(1, 4, 2);
		bt.addBox(4, 4, 2);
		System.out.println(bt.solve());
		/*
		Scanner in = new Scanner(System.in);
		try {
			int lines = in.nextInt();
			//System.out.println("lines:" + lines + "\n");
			if (lines < 0) return;
			while (lines > 0) {
				int l = in.nextInt();
				int w = in.nextInt();
				int h = in.nextInt();
				bt.addBox(l,  w, h);
				//System.out.println("box:" + l + " " + w + " " + h + " " + "\n");
				lines--;
			}
		}
		catch (Exception e) {
			return;
		}
		System.out.println(bt.solve());
		*/
		return;
	}
	
	public Solution() {
		_v = new Vector<Box>();
	}
	
	public int solve() { return solve(new Vector<Box>(), 0, _v, 0); }
	
	public int solve(Vector<Box> t, int h, Vector<Box> r, int s) {
		if (s >= r.size()) return h;
		Vector<Box> t0 = t;
		int h0 = solve(t, h, r, s + 1);
		Vector<Box> v = r.get(s).permute();
		for (int i = 0; i < v.size(); i++) {
			t = t0;
			if (insert(t, v.get(i), 0, t.size() - 1)) {
				int hi = solve(t, h + v.get(i)._h, r, s + 1);
				if (h0 < hi) h0 = hi;
			}
		}
		return h0;
	}
	
	private boolean insert(Vector<Box> v, Box b, int i, int j) {
		if (v.size() == 0 || i == j) {
			v.add(i, b);
			return true;
		}
	
		int x = (i + j) / 2;
		if (v.get(x)._l <= b._l && v.get(x)._w <= b._w) {
			if (i == x) {
				v.add(j, b);
				return true;
			}
			return insert(v, b, x, j);
		}
		if (v.get(x)._l >= b._l && v.get(x)._w >= b._w) {
			return insert(v, b, i, x);
		}
		return false;
	}
	
	public class Box {
		public Box(int l, int w, int h) {
			_l = l;
			_w = w;
			_h = h;
		}
		public Vector<Box> permute() {
			Vector<Box> v = new Vector<Box>();
			v.add(new Box(_l, _w, _h));
			v.add(new Box(_l, _h, _w));
			v.add(new Box(_w, _l, _h));
			v.add(new Box(_w, _h, _l));
			v.add(new Box(_h, _l, _w));
			v.add(new Box(_h, _w, _l));
			return v;
		}
		
		public int _l;
		public int _w;
		public int _h;
	};
	
	public void addBox(int l, int w, int h) {
		_v.add(new Box(l, w, h));
	}
	
	private Vector<Box> _v;
}
