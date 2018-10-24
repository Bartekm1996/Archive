#include "colors.inc"
#include "stones.inc"
#include "textures.inc"
#include"math.inc"

#declare _radius = 1.0;
#declare cut_Location = 0.75*_radius;
#declare centroid =div((3*((2*(_radius)) -((_radius-cut_Location)))),(4*((3*(_radius)) -((_radius-cut_Location)))));
#declare cut_edge_radius = sqrt((_radius-cut_Location)*((2*(_radius))	- (_radius-cut_Location)));
#declare center_gravity = <centroid,centroid,centroid>;
#declare cty = vrotate(<cut_Location,cut_edge_radius,0>,<0,0, -clock*360>);
#declare vecx = vrotate(<centroid-cut_Location,centroid+cut_edge_radius,centroid-0>,<0,0, -clock*360> );
#declare vecy = vrotate(<centroid+cut_Location,centroid-cut_edge_radius,centroid-0>,<0,0, -clock*360> );


camera {
	location <0, 2*_radius, -7*_radius>
	look_at <0, 0, 0>
}

light_source { <15, 15, -2> color White }
//visulizing the centroid to the point
//plane { vecx, 0 
//	pigment {
//		checker color White, color Green
//		}
//	translate <-pi, _radius, 0>
//	translate <2*pi*clock, 0, 0>
//}
difference {
	sphere {
		<0, 0, 0>,_radius
		texture {
			Blood_Marble
			finish { phong 1
			specular .75
			roughness .001
			
			}
		}
	}
	box {
		_radius,<-cut_Location, cut_Location, -cut_Location>
		texture {
			Blood_Marble
			finish { phong 1 
			 specular .75
			roughness .001
			reflection {
			   .5
			}
			}
		}
	}

	
	#if (vdot(vnormalize(y),vecx)>0)
	rotate <0,0, -clock*360>
	translate <-pi, _radius, 0>
	translate <2*pi*clock, 0, 0>
	#end
	#if (vdot(vnormalize(y),vecx)<=0)
	rotate <0,0,-180>
	translate<0,cut_Location, 0>
	#end
}

plane { y, 0 
	pigment {
		checker color White, color Green
		}
}


