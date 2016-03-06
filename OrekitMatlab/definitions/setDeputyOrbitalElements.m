function [deputy,date] = setDeputyOrbitalElements(mu,i)


switch i
     case 1 %CanX-4:
        % date: 2014.07.04 16:55:41.240
        n = 14.73345459;
        a = (mu/(n*2*pi/(24*3600))^2)^(1/3);
        e = 0.0012952;
        %e = 0.01;
        in = 98.2557/180*pi;
        omega = 258.543/180*pi;
        raan = 252.93/180*pi;
        mean_anomaly = 101.433/180*pi;
    case 2 %CanX-5:
        % date: 2014.07.04 18:32:30.036
        n = 14.73568733;
        a = (mu/(n*2*pi/(24*3600))^2)^(1/3);
        e = 0.001037;
        in = 98.2534/180*pi;
        omega = 255.869/180*pi;
        raan = 252.995/180*pi;
        mean_anomaly = 104.137;%/180*pi;
    case 3 %UWE-3: %date: 2014.01.01 00:00:00.000
        a = 7019120.191;
        e = 0.007879;
        in =  97.873/180*pi;
        omega = 71.994/180*pi;
        raan = 76.949/180*pi;
        mean_anomaly = 340.151/180*pi;
    case 4 % fun cube 4/12/2013 11:00:00
        a = 7016995.871;	
        e = 0.006422; 
        in = 97.859*pi/180; 
        omega = 145.12*pi/180; 
        raan = 50.485*pi/180; 
        ta=68.643*pi/180; 
        mean_anomaly = 67.959*pi/180;
        date = [2013, 12, 04, 11, 00, 00.000];
    case 5 % Shaubs deputy - from his paper
        %these are mean oes%%%%%
        chief = [7555000;0.0500000000000000;deg2rad(48);deg2rad(10);deg2rad(20);0;deg2rad(120)];
        delta = [-100; 0; 0.05*pi/180; 0; -0.01*pi/180; 0; 0];
        deputy = chief+delta;
        
        true_anomaly = meanAnomToTrueAnom(deputy(2), deputy(7) );
        deputy(6) = true_anomaly;
        ta = deputy(6);
        %%%must convert to osc OEs before sending to orekit
        %deputy = convertMeanOeToOscOe( deputy );
        a = deputy(1);
        e = deputy(2);
        in = deputy(3);
        omega = deputy(4);
        raan = deputy(5);
        mean_anomaly = deputy(6);
        date = [2014, 01, 01, 00, 00, 00.000];
    case 6 % Shaubs deputy - from his paper
%         oecBase = [7555000;0.0500000000000000;deg2rad(48);deg2rad(10);deg2rad(20);0;deg2rad(120)];
%         oecBase(6) = meanAnomToTrueAnom(oecBase(2), oecBase(7) );
%         
%          delta = [-63.38115; 5.6267e-05; -8.7266e-06; 5.6267e-03; -4.8267e-04; 0; 0];
%         oec_temp = oecBase+delta;
%         oecm_temp  = convertOscOeToMeanOe( oec_temp );
%         oed_delta = [-100+0.649;0;deg2rad(0.05);0;deg2rad(-0.01);0;0];
%         oed_temp=oec_temp+oed_delta;
%         oedm_temp = convertOscOeToMeanOe( oed_temp );
         oed_temp = [7554837.26050148;0.0500573549706241;0.838621978983275;0.180138599523167;0.348408647473666;2.17829277589629;2.09441679972112];
        
        
        ta = oed_temp(6);
        %%%must convert to osc OEs before sending to orekit
        %deputy = convertMeanOeToOscOe( deputy );
        a = oed_temp(1);
        e = oed_temp(2);
        in = oed_temp(3);
        omega = oed_temp(4);
        raan = oed_temp(5);
        mean_anomaly = oed_temp(7);
        date = [2014, 01, 01, 00, 00, 00.000];
end

deputy = [a,e,in,omega,raan,ta,mean_anomaly];
