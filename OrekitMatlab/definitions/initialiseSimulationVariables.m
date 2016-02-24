function [startingDate,a,e,in,omega,raan,mean_anomaly,numberOfThrusters, thrust, numberOfThrusters, startingMass] = initialiseSimulationVariables(muValue)
%% global variables
global timeVector
global Isp thrust mass
global mu
global req j2 g thrustDurationLimit;
global position_tolerance min_step max_step duration step_size choiceofProp;
%global ii %loop variable
global oed oec oedm oecm oeError;%orbital elements of deputy and chief arrays (also mean eles)
global fireA fireB fireC fireD fireThruster thrustVector;
global dVA dVB dVC dVD;

global AThrustVector BThrustVector CThrustVector DThrustVector;
global tABoostStartCommand tBBoostStartCommand tCBoostStartCommand tDBoostStartCommand;
global tABoostEndCommand tBBoostEndCommand tCBoostEndCommand tDBoostEndCommand;
%setMu(mu);
mu = muValue;
oeError = [0; 0; 0; 0; 0; 0; 0]; %maybe should calculate this properly for the starting conditions, dont forget to use mean elements
timeVector = datetime(0001,01,01,000,00,00);
tABoostStartCommand = datetime(0001,01,01,000,00,00);
tBBoostStartCommand = datetime(0001,01,01,000,00,00);
tCBoostStartCommand = datetime(0001,01,01,000,00,00);
tDBoostStartCommand = datetime(0001,01,01,000,00,00);
tABoostEndCommand = datetime(0001,01,01,000,00,00);
tBBoostEndCommand = datetime(0001,01,01,000,00,00);
tCBoostEndCommand = datetime(0001,01,01,000,00,00);
tDBoostEndCommand = datetime(0001,01,01,000,00,00);
AThrustVector = [0;0;0];
BThrustVector = [0;0;0];
CThrustVector = [0;0;0];
DThrustVector = [0;0;0];
fireThruster = 0;
fireA = 0; fireB = 0; fireC = 0; fireD = 0;
dVA = [0;0;0]; dVB = [0;0;0]; dVC = [0;0;0]; dVD = [0;0;0]; 
[position_tolerance, min_step, max_step, duration, step_size, choiceofProp] = setNumericalPropagatorSettings();
global pos vel;
pos = [0;0;0];
vel = [0;0;0];
mass = 0;
%chief
%hincubeOE on 4/12/2013
%oec = setChiefOrbitalElements(1)

%Chief OE from Schaubs paper
oec = setChiefOrbitalElements(2)
oecm = oec; %just assume this for now - later this hsould be changed to get the actual mean cheif orbital elements
%initialise deputy OE
oed = zeros(7,1);
oedm = zeros(7,1);
%set initial deputy OEs and the date
[a,e,in,omega,raan,mean_anomaly,startingDate] = setDeputyOrbitalElements(mu,1);



%thruster operating point
Isp = 2000;%s
numberOfThrusters = 1;
thrust = 0.04; %N per thruster
thrustDurationLimit = 180; %seconds
%mu = 3.986004415000000e+14;
req = 6378.137; %WGS84_EARTH_EQUATORIAL_RADIUS
j2 = 1.08262668355e-3;
g = 9.80665; %m/s^2
startingMass = 1; %assume initial mass of sc is 1kg + the fuel of one thruster

