clear all; clc;
%% global variables
global timeVector
global Isp thrust mass
global step_size duration req j2 g mu thrustDurationLimit;
global ii %loop variable
global oed oec oedm oecm oeError;%orbital elements of deputy and chief arrays (also mean eles)
global fireA fireB fireC fireD fireThruster thrustVector;
global dVA dVB dVC dVD;
global tABoostStartCommand tBBoostStartCommand tCBoostStartCommand tDBoostStartCommand;
global tABoostEndCommand tBBoostEndCommand tCBoostEndCommand tDBoostEndCommand;
oeError = [0; 0; 0; 0; 0; 0; 0]; %maybe should calculate this properly for the starting conditions, dont forget to use mean elements
tABoostStartCommand = datetime(0001,01,01,000,00,00);
tBBoostStartCommand = datetime(0001,01,01,000,00,00);
tCBoostStartCommand = datetime(0001,01,01,000,00,00);
tDBoostStartCommand = datetime(0001,01,01,000,00,00);
tABoostEndCommand = datetime(0001,01,01,000,00,00);
tBBoostEndCommand = datetime(0001,01,01,000,00,00);
tCBoostEndCommand = datetime(0001,01,01,000,00,00);
tDBoostEndCommand = datetime(0001,01,01,000,00,00);
ii = 2;
fireThruster = 0;
fireA = 0; fireB = 0; fireC = 0; fireD = 0;
dVA = [0;0;0]; dVB = [0;0;0]; dVC = [0;0;0]; dVD = [0;0;0]; 
[position_tolerance, min_step, max_step, duration, step_size, choiceofProp] = setNumericalPropagatorSettings();
%chief
%hincubeOE on 4/12/2013
oec = [7017102.334; 0.006535; 97.858*pi/180; 145.60397*pi/180; 50.48597*pi/180; 67.871*pi/180; 67.17897*pi/180];
oec = repmat(oec,1,ceil(duration/step_size)); %orbital elements of chief, each column is a new time step
oecm = oec; %just assume this for now - later this hsould be changed to get the actual mean cheif orbital elements

% a = orbital_elements_chief(1);
% e = orbital_elements_chief(2);
% i = orbital_elements_chief(3);
% AoP = orbital_elements_chief(4);
% RAAN = orbital_elements_chief(5);
% f = orbital_elements_chief(6);
% M = orbital_elements_chief(7);
oed = zeros(7, ceil(duration/step_size));
oedm = oed;
%thruster operating point
Isp = 2000;%s
thrust = 5e-6; %N
thrustDurationLimit = 180; %seconds
mu = 3.986004415000000e+14;
req = 6378.14; %
j2 = 1.08262668355e-3;
g = 9.80665; %m/s^2
mass = 1+0.00025; %assume initial mass of sc is 1kg + the fuel of one thruster

timeVector = datetime(0001,01,01,000,00,00);