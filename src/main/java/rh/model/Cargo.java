package rh.model;

public enum Cargo {

	ASSISTENTE{
		@Override
		public Cargo getProximoCargo(){
			return ESPECIALISTA;
		}
	},
	ANALISTA{
		@Override
		public Cargo getProximoCargo(){
			return ASSISTENTE;
		}
	},
	ESPECIALISTA{
		@Override
		public Cargo getProximoCargo(){
			return GERENTE;
		}
	},
	GERENTE{
		@Override
		public Cargo getProximoCargo(){
			return GERENTE;
		}
	};

    public abstract Cargo getProximoCargo();
}
