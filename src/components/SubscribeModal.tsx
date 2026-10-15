import React, { useState } from 'react';
import { X, Check, ShieldCheck, Newspaper, Sparkles } from 'lucide-react';

interface SubscribeModalProps {
  onClose: () => void;
  onSubscribeSuccess: (email: string) => void;
}

export const SubscribeModal: React.FC<SubscribeModalProps> = ({
  onClose,
  onSubscribeSuccess,
}) => {
  const [email, setEmail] = useState('');
  const [selectedPlan, setSelectedPlan] = useState<'basic' | 'all-access'>('all-access');

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (!email.trim()) return;
    onSubscribeSuccess(email.trim());
    onClose();
  };

  return (
    <div className="fixed inset-0 z-50 bg-black/60 backdrop-blur-xs flex items-center justify-center p-4">
      <div className="bg-white rounded-lg shadow-2xl w-full max-w-lg overflow-hidden border border-gray-300">
        <div className="p-4 border-b border-nyt flex justify-between items-center bg-gray-50">
          <div className="font-masthead text-2xl text-black">The New York Times</div>
          <button
            onClick={onClose}
            className="p-1 hover:bg-gray-200 rounded text-gray-500 hover:text-black cursor-pointer"
          >
            <X className="w-5 h-5" />
          </button>
        </div>

        <div className="p-6">
          <div className="text-center mb-6">
            <span className="text-[11px] uppercase tracking-widest font-bold text-[#b82218] block mb-1">
              Limited Time Special Offer
            </span>
            <h3 className="font-headline text-3xl font-bold text-black mb-2">
              Subscribe for $1/week
            </h3>
            <p className="font-headline text-sm text-gray-600">
              Unlimited access to independent journalism, investigative reporting, daily games, recipes, and product testing.
            </p>
          </div>

          {/* Plan Options */}
          <div className="space-y-3 mb-6">
            <div
              onClick={() => setSelectedPlan('all-access')}
              className={`p-4 rounded border-2 cursor-pointer transition-all ${
                selectedPlan === 'all-access'
                  ? 'border-black bg-gray-50/60 shadow-xs'
                  : 'border-gray-200 hover:border-gray-300'
              }`}
            >
              <div className="flex justify-between items-center mb-1">
                <span className="font-bold text-sm text-black flex items-center gap-1.5">
                  <Sparkles className="w-4 h-4 text-amber-600" />
                  All Access Digital
                </span>
                <span className="font-bold text-base text-black">$1 / week</span>
              </div>
              <p className="text-xs text-gray-500">
                News + Games (Wordle & Crosswords) + Cooking + Wirecutter + The Athletic
              </p>
            </div>

            <div
              onClick={() => setSelectedPlan('basic')}
              className={`p-4 rounded border-2 cursor-pointer transition-all ${
                selectedPlan === 'basic'
                  ? 'border-black bg-gray-50/60 shadow-xs'
                  : 'border-gray-200 hover:border-gray-300'
              }`}
            >
              <div className="flex justify-between items-center mb-1">
                <span className="font-bold text-sm text-black flex items-center gap-1.5">
                  <Newspaper className="w-4 h-4 text-gray-600" />
                  News Only
                </span>
                <span className="font-bold text-base text-black">$0.75 / week</span>
              </div>
              <p className="text-xs text-gray-500">
                Full digital access to The New York Times news reporting
              </p>
            </div>
          </div>

          <form onSubmit={handleSubmit} className="space-y-3">
            <div>
              <label className="block text-xs font-semibold text-gray-700 mb-1">
                Email Address
              </label>
              <input
                type="email"
                required
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                placeholder="your.email@example.com"
                className="w-full p-2.5 border border-gray-300 rounded text-sm focus:outline-none focus:border-black font-sans-editorial"
              />
            </div>

            <button
              type="submit"
              className="w-full bg-[#567b95] hover:bg-[#326891] text-white font-bold text-xs py-3 rounded uppercase tracking-wider transition-colors cursor-pointer"
            >
              Start Subscription for $1/week
            </button>
          </form>

          <div className="mt-4 flex items-center justify-center gap-1 text-[11px] text-gray-500">
            <ShieldCheck className="w-3.5 h-3.5 text-gray-400" />
            <span>Cancel or pause anytime online. Terms apply.</span>
          </div>
        </div>
      </div>
    </div>
  );
};
