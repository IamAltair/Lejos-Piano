package PianoRobot;
import lejos.hardware.ev3.EV3;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;


class Tangenter {
	double bpm;
	double mpb;
	double standarNote;
	double i = 0;
	double lengdeCM;
	double vei = 0;

	Ultrasonic ultra = new Ultrasonic();

	double v = 0;
	double h = 0;
	long a = 0;
	double c = 0;

	public Tangenter(double lengde, double bpm, double standarNote) {
		this.bpm = bpm;
		this.lengdeCM = lengdeCM;
		Motor.A.setSpeed(900);
		Motor.B.setSpeed(900);
		Motor.C.setSpeed(900);
		Motor.D.setSpeed(900);

		mpb = 60/bpm*standarNote*4*1000;
	}

	public void spillNote(char note, int oktav, boolean skarp, double lengde) throws Exception {
		bevegTilAvstand(note, oktav, skarp);
		fingering(lengde);
	}

	public void spillNoteOpt(double lengde, double noteOpt) throws Exception {
		bevegTilAvstandOpt(noteOpt);
		fingering(lengde);
	}

	public void fingering(double lengde) throws Exception {
		if (v<h) {
			fingeringH(lengde);
		} else {fingeringV(lengde);}
	}

	public void fingeringH(double lengde) throws Exception {
		Motor.C.rotate(90);
		c = mpb*lengde;
		long a = (long) c;
		Thread.sleep(a);
		Motor.C.rotateTo(100);
	}

	public void fingeringV(double lengde) throws Exception {
		Motor.D.rotate(90);
		c = mpb*lengde;
		long a = (long) c;
		Thread.sleep(a);
		Motor.D.rotateTo(100);
	}

	public void bevegTilAvstand(char note, int oktav, boolean skarp) {
		if(finnVeiV(note, oktav, skarp) >= finnVeiH(note, oktav, skarp)) {
			vei = finnVeiV(note, oktav, skarp);
			} else {vei = finnVeiH(note, oktav, skarp);}
			ultra.runSample();

		if(vei>=ultra.getUltraSample()) {
			while(vei>=ultra.getUltraSample()){
				ultra.runSample();
				Motor.A.forward();
				Motor.B.backward();
			}
		}

		else {
			while(vei<=ultra.getUltraSample()){
				ultra.runSample();
				Motor.A.backward();
				Motor.B.forward();
			}
		}

	}

	public void bevegTilAvstandOpt(double noteOpt) {
				ultra.runSample();
				v = noteOpt+5;
				h = noteOpt-5;
				if(v>=h) {
					vei = v;
				} else {vei = h;}

				if(vei>=ultra.getUltraSample()) {
					while(vei>=ultra.getUltraSample()){
						ultra.runSample();
						Motor.A.forward();
						Motor.B.backward();
					}
				}

				else {
					while(vei<=ultra.getUltraSample()){
						ultra.runSample();
						Motor.A.backward();
						Motor.B.forward();
					}
				}

	}



	public double finnVeiV(char note, int oktav, boolean skarp) {
		return 36/lengdeCM*noteTilVerdi(note, oktav, skarp)+1+5;
	}

	public double finnVeiH(char note, int oktav, boolean skarp) {
		return 37/lengdeCM*noteTilVerdi(note, oktav, skarp)+18+5;
	}

	public double noteTilVerdi(char note, int oktav, boolean skarp) {
		i = 0;
		switch (note) {
					case 'H':  i = 2;
							 break;
					case 'C':  i = 3;
							 break;
					case 'D':  i=4;
							 break;
					case 'E':  i=5;
							 break;
					case 'F':  i=6;
							 break;
					default: i=7;
							break;
		}
		i = i*oktav;
		if (skarp = true || i <= 37) {
			i += 0.5;
		}
		return i;
	}
}