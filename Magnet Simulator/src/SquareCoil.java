public class SquareCoil extends Coil {
    public SquareCoil(double radius, double length, int precision, double current, double z){//length should always be at least twice radius
        double curvedLength = Math.PI*radius*2;
        double straightLength = length*4-(8*radius);
        double totalLength = curvedLength+straightLength;
        int curvedSegments = (int)Math.ceil((precision*curvedLength)/(totalLength)/4)*4;
        int straightSegments = (int)Math.ceil((precision*straightLength)/(totalLength)/4)*4;
        double segmentLength = totalLength/(curvedSegments+straightSegments);
        double l = (Math.PI * 2)/curvedSegments;
        MagnetSegment[] segments = new MagnetSegment[curvedSegments + straightSegments];
        double angle = 0;
        int total = 0;
        //calculates the positions of all the segments, alternating between corners and edges
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner +x +y
            angle += l;
            MagnetSegment next = new MagnetSegment(segmentLength, Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius + length / 2 - radius, z, -angle, 0, current);
            //System.out.println((Math.sin(angle) * radius + length / 2 - radius) + " " + (Math.cos(angle) * radius + length / 2 - radius) + " " + angle);
            segments[total] = next;
            total++;
        }
        angle = Math.PI/2;
        //side +x
        for(int count = 0; count <straightSegments/4; count++){
            MagnetSegment next = new MagnetSegment(segmentLength, length/2, length/2-(4*count*(length-2*radius)/straightSegments)-radius, z, -angle, 0, current);
            segments[total] = next;
            total++;
        }
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner +x -y
            angle += l;
            MagnetSegment next = new MagnetSegment(segmentLength, Math.sin(angle) * radius + length / 2 - radius, Math.cos(angle) * radius - length / 2 + radius, z, -angle, 0, current);
            //System.out.println((Math.sin(angle) * radius + length / 2 - radius) + " " + (Math.cos(angle) * radius + length / 2 - radius) + " " + angle);
            segments[total] = next;
            total++;
        }
        angle = Math.PI;
        for(int count = 0; count <straightSegments/4; count++){
            MagnetSegment next = new MagnetSegment(segmentLength, length/2-(4*count*(length-2*radius)/straightSegments)-radius, -length/2, z, -angle, 0, current);
            segments[total] = next;
            total++;
        }
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner -x -y
            angle += l;
            MagnetSegment next = new MagnetSegment(segmentLength, (Math.sin(angle) * radius + length / 2 + radius)-length, Math.cos(angle) * radius - length / 2 + radius, z, -angle, 0, current);
            //System.out.println((Math.sin(angle) * radius + length / 2 - radius) + " " + (Math.cos(angle) * radius + length / 2 - radius) + " " + angle);
            segments[total] = next;
            total++;
        }
        angle = Math.PI*3/2;
        for(int count = 0; count <straightSegments/4; count++){
            //side -x
            MagnetSegment next = new MagnetSegment(segmentLength, -length/2, (4*count*(length-2*radius)/straightSegments)+radius-length/2, z, -angle, 0, current);
            segments[total] = next;
            total++;
        }
        for(int count = 0; count <curvedSegments/4; count++) {
            //corner -x +y
            angle += l;
            MagnetSegment next = new MagnetSegment(segmentLength, Math.sin(angle) * radius + length / 2 + radius - length, Math.cos(angle) * radius + length / 2 - radius, z, -angle, 0, current);
            //System.out.println((Math.sin(angle) * radius + length / 2 - radius) + " " + (Math.cos(angle) * radius + length / 2 - radius) + " " + angle);
            segments[total] = next;
            total++;
        }
        angle = Math.PI*2;
        for(int count = 0; count <straightSegments/4; count++){
            MagnetSegment next = new MagnetSegment(segmentLength, (4*count*(length-2*radius)/straightSegments)+radius-length/2, length/2, z, -angle, 0, current);
            segments[total] = next;
            total++;
        }
        super.setSegments(segments);
    }
}